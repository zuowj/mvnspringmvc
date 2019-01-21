# mvnspringmvc
这是一个基于maven+springMVC+springJDBC的demo网站，目的是为了让一些初学者可以根据此DEMO进行学习，以便可以尽快上手！
***
该DEMO的搭建过程与涉及的知识点，可以参见我的这篇博文：
<a href="https://www.cnblogs.com/zuowj/p/10095753.html" target="_blank">JAVA WEB快速入门之从编写一个基于SpringMVC框架的网站了解Maven、SpringMVC、SpringJDBC</a>

示例中使用的两张表（TA_TestPost、TA_TestPostComment）的SQL SERVER SQL脚本如下：
```sql
CREATE TABLE [dbo].[TA_TestPost](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](40) NOT NULL,
	[content] [nvarchar](max) NULL,
	[author] [nvarchar](16) NULL,
	[createTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[TA_TestPost] ADD  DEFAULT (getdate()) FOR [createTime]
GO


CREATE TABLE [dbo].[TA_TestPostComment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[postid] [int] NOT NULL,
	[content] [nvarchar](max) NULL,
	[createby] [nvarchar](16) NULL,
	[createTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[TA_TestPostComment] ADD  DEFAULT (getdate()) FOR [createTime]
GO

```

