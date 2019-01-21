package cn.zuowenjun.java.mvc.controller;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.zuowenjun.java.mvc.model.Post;
import cn.zuowenjun.java.mvc.model.PostComment;
import cn.zuowenjun.java.mvc.service.*;
/**
 * 
 * @author zuowenjun.cn
 *refer-mavendepconfig:https://blog.csdn.net/qq_29227939/article/details/52063869
 *refer-EL:http://www.cnblogs.com/dongfangshenhua/p/6731421.html
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostCommentService postCommentService;
	
	@RequestMapping()
	public ModelAndView list() {
		List<Post> postList= postService.getAll();
		ModelAndView mv=new ModelAndView();
		mv.addObject("posts",postList);
		mv.setViewName("bloglist");

		return mv;
	}
	
	@RequestMapping(path="/querylist",method=RequestMethod.POST)
	public ModelAndView list(@RequestParam(required=true) Date frmDate,@RequestParam(required=true) Date toDate,ModelAndView mv) {
		List<Post> postList=postService.getList(frmDate, toDate);
		mv.setViewName("bloglist");
		mv.addObject("posts",postList);
		return mv;
	}
	
	@RequestMapping("/post/{postid}")
	public String detail(@PathVariable String postid,ModelMap model) {
		int pid=Integer.parseInt(postid);
		model.put("post", postService.get(pid));
		model.put("comments", postCommentService.getList(pid));
		
		return "blogdetail";
	}

	
	@RequestMapping(path="/savecomment",method=RequestMethod.POST)
	public String saveComment(@ModelAttribute() PostComment postComment,RedirectAttributes redirectAttr) {
		String resultMsg="���۱���ɹ�";
		if(!postCommentService.create(postComment)) {
			resultMsg="���۱���ʧ�ܣ����Ժ�����";
		}
		redirectAttr.addFlashAttribute("msg", resultMsg);
		
		return "redirect:/blog/post/" + postComment.getPostid();
	}
	
	@RequestMapping(path="/editpost/{postid}",method=RequestMethod.GET)
	public ModelAndView editPost(@PathVariable(required=true) int postid) {
		ModelAndView mv=new ModelAndView();
		Post post=null;
		post=postService.get(postid);
		if(post==null) {
			throw new InvalidParameterException("��Ч��postid");
		}
		mv.addObject("post", post);
		mv.setViewName("blogedit");
		
		return mv;
	}
	
	@RequestMapping("/editpost")
	public String createPost(Map<String,Object> viewDataMap) {
		Post post=new Post();
		viewDataMap.put("post", post);
		return "blogedit";
	}
	
	@RequestMapping(path="/editpost",method=RequestMethod.POST)
	public String updatePost(@ModelAttribute("post") Post post,@RequestParam("doAction") String action,Model model,
			HttpServletResponse reponse) throws IOException {
		
		String result="����ɹ���";
		if(action.equals("delete")) { //ɾ������
			if(!postService.delete(post.getId())){
				result="ɾ��ʧ�ܣ������ԣ�";
			}else {
				String jsResult="<script>alert('ɾ���ɹ�!');self.close();</script>";
				reponse.setContentType("text/html;charset=utf-8");
				reponse.getWriter().append(jsResult);
				return null;
			}
		}
		else { //�༭����
			
			if(post.getId()<=0) { //���������߼�
				int postId= postService.create(post);
				if(postId>0) {
					post.setId(postId);
				}else {
					result="����ʧ�ܣ������ԣ�";
				}
			}else if(!postService.update(post)) { //���²����߼�
				result="����ʧ�ܣ������ԣ�";
			}
		}
		model.addAttribute("result", result);
		return "blogedit";
	}

	
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		
		//ת������
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditorΪ�Զ������ڱ༭��
	}
	
}
