USE [master]
GO
/****** Object:  Database [appData]    Script Date: 2017-07-16 10:18:24 PM ******/
CREATE DATABASE [appData]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'appData', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\appData.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'appData_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\appData_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [appData] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [appData].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [appData] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [appData] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [appData] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [appData] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [appData] SET ARITHABORT OFF 
GO
ALTER DATABASE [appData] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [appData] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [appData] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [appData] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [appData] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [appData] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [appData] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [appData] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [appData] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [appData] SET  DISABLE_BROKER 
GO
ALTER DATABASE [appData] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [appData] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [appData] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [appData] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [appData] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [appData] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [appData] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [appData] SET RECOVERY FULL 
GO
ALTER DATABASE [appData] SET  MULTI_USER 
GO
ALTER DATABASE [appData] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [appData] SET DB_CHAINING OFF 
GO
ALTER DATABASE [appData] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [appData] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [appData] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'appData', N'ON'
GO
ALTER DATABASE [appData] SET QUERY_STORE = OFF
GO
USE [appData]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [appData]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[accountId] [int] IDENTITY(1,1) NOT NULL,
	[companyId] [int] NULL,
	[startDate] [datetime] NOT NULL,
	[updateDate] [datetime] NULL,
	[parentAccountId] [int] NULL,
	[statusId] [int] NOT NULL,
 CONSTRAINT [Account_PK] PRIMARY KEY CLUSTERED 
(
	[accountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Company]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Company](
	[companyId] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](200) NULL,
	[phoneNumber] [varchar](20) NULL,
	[email] [varchar](50) NULL,
	[country] [varchar](50) NULL,
	[website] [varchar](200) NULL,
	[uuid] [varchar](50) NULL,
	[externalId] [varchar](50) NULL,
	[createdDate] [datetime] NOT NULL,
	[updateDate] [datetime] NULL,
 CONSTRAINT [Company_PK] PRIMARY KEY CLUSTERED 
(
	[companyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Creator]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Creator](
	[creatorId] [int] IDENTITY(1,1) NOT NULL,
	[firstName] [varchar](100) NULL,
	[lastName] [varchar](100) NULL,
	[fullName] [varchar](200) NULL,
	[email] [varchar](100) NULL,
	[language] [varchar](20) NULL,
	[locale] [varchar](20) NULL,
	[openId] [varchar](200) NULL,
	[uuid] [varchar](50) NULL,
	[createdDate] [datetime] NOT NULL,
	[updateDate] [datetime] NULL,
 CONSTRAINT [Creator_PK] PRIMARY KEY CLUSTERED 
(
	[creatorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CreatorAddress]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CreatorAddress](
	[uniqueId] [int] IDENTITY(1,1) NOT NULL,
	[creatorId] [int] NOT NULL,
	[street2] [varchar](100) NULL,
	[state] [varchar](50) NULL,
	[city] [varchar](50) NULL,
	[country] [varchar](50) NULL,
	[zip] [varchar](25) NULL,
	[createdDate] [datetime] NOT NULL,
	[updatedDate] [datetime] NULL,
	[street1] [varchar](100) NULL,
 CONSTRAINT [CreatorAddress_PK] PRIMARY KEY CLUSTERED 
(
	[uniqueId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Edition]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Edition](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](100) NULL,
	[shortDescription] [varchar](50) NULL,
	[description] [varchar](100) NULL,
	[startDate] [datetime] NOT NULL,
	[endDate] [datetime] NULL,
 CONSTRAINT [Edition_PK] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EventType]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EventType](
	[Id] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[code] [varchar](100) NULL,
	[shortDescription] [varchar](50) NULL,
	[description] [varchar](100) NULL,
	[startDate] [datetime] NOT NULL,
	[endDate] [datetime] NULL,
 CONSTRAINT [EventType_PK] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[marketPlace]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[marketPlace](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[partnerName] [varchar](100) NULL,
	[baseUrl] [varchar](200) NULL,
	[description] [varchar](100) NULL,
	[startDate] [datetime] NOT NULL,
	[endDate] [datetime] NULL,
 CONSTRAINT [marketPlace_PK] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[orderTypeId] [int] NOT NULL,
	[startDate] [datetime] NOT NULL,
	[completionDate] [datetime] NULL,
	[creatorId] [int] NULL,
	[marketPlaceId] [int] NOT NULL,
	[accountId] [int] NOT NULL,
	[edition] [varchar](50) NULL,
	[pricingDuration] [varchar](50) NULL,
 CONSTRAINT [Order_PK] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderType]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderType](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](100) NULL,
	[shortDescription] [varchar](50) NULL,
	[description] [varchar](100) NULL,
	[startDate] [datetime] NOT NULL,
	[endDate] [datetime] NULL,
 CONSTRAINT [OrderType_PK] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PricingDuration]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PricingDuration](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](100) NULL,
	[shortDescription] [varchar](50) NULL,
	[description] [varchar](100) NULL,
	[startDate] [datetime] NOT NULL,
	[endDate] [datetime] NULL,
 CONSTRAINT [PricingDuration_PK] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](100) NULL,
	[shortDescription] [varchar](50) NULL,
	[description] [varchar](100) NULL,
	[startDate] [datetime] NOT NULL,
	[endDate] [datetime] NULL,
 CONSTRAINT [Status_PK] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[firstName] [varchar](100) NULL,
	[lastName] [varchar](100) NULL,
	[fullName] [varchar](200) NULL,
	[email] [varchar](100) NULL,
	[language] [varchar](20) NULL,
	[locale] [varchar](20) NULL,
	[openId] [varchar](200) NULL,
	[uuid] [varchar](50) NULL,
	[createdDate] [datetime] NOT NULL,
	[updateDate] [datetime] NULL,
	[accountId] [int] NOT NULL,
	[statusId] [int] NOT NULL,
 CONSTRAINT [User_PK] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserAddress]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserAddress](
	[uniqueId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[street2] [varchar](100) NULL,
	[state] [varchar](50) NULL,
	[country] [varchar](50) NULL,
	[zip] [varchar](25) NULL,
	[createdDate] [datetime] NOT NULL,
	[updatedDate] [datetime] NULL,
	[city] [varchar](50) NULL,
	[street1] [varchar](100) NULL,
 CONSTRAINT [UserAddress_PK] PRIMARY KEY CLUSTERED 
(
	[uniqueId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserEvent]    Script Date: 2017-07-16 10:18:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserEvent](
	[eventId] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[eventTypeId] [int] NOT NULL,
	[startDate] [datetime] NOT NULL,
	[completionDate] [datetime] NULL,
	[creatorId] [int] NULL,
	[marketPlaceId] [int] NOT NULL,
	[accountId] [int] NOT NULL,
	[userId] [int] NOT NULL,
 CONSTRAINT [Orderv1_PK] PRIMARY KEY CLUSTERED 
(
	[eventId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([accountId], [companyId], [startDate], [updateDate], [parentAccountId], [statusId]) VALUES (18, 18, CAST(N'2017-07-15T15:37:52.263' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), NULL, 6)
INSERT [dbo].[Account] ([accountId], [companyId], [startDate], [updateDate], [parentAccountId], [statusId]) VALUES (19, 19, CAST(N'2017-07-15T19:13:09.840' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), NULL, 7)
INSERT [dbo].[Account] ([accountId], [companyId], [startDate], [updateDate], [parentAccountId], [statusId]) VALUES (20, 20, CAST(N'2017-07-15T19:13:26.143' AS DateTime), NULL, NULL, 5)
INSERT [dbo].[Account] ([accountId], [companyId], [startDate], [updateDate], [parentAccountId], [statusId]) VALUES (21, 21, CAST(N'2017-07-15T19:13:37.593' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), NULL, 7)
INSERT [dbo].[Account] ([accountId], [companyId], [startDate], [updateDate], [parentAccountId], [statusId]) VALUES (22, 18, CAST(N'2017-07-15T22:58:37.917' AS DateTime), NULL, 18, 5)
INSERT [dbo].[Account] ([accountId], [companyId], [startDate], [updateDate], [parentAccountId], [statusId]) VALUES (23, 22, CAST(N'2017-07-16T11:49:12.510' AS DateTime), NULL, NULL, 5)
SET IDENTITY_INSERT [dbo].[Account] OFF
SET IDENTITY_INSERT [dbo].[Company] ON 

INSERT [dbo].[Company] ([companyId], [name], [phoneNumber], [email], [country], [website], [uuid], [externalId], [createdDate], [updateDate]) VALUES (18, N'tester', NULL, NULL, N'US', N'www.testco.com', N'385beb51-51ae-4ffe-8c05-3f35a9f99825', NULL, CAST(N'2017-07-15T15:37:52.260' AS DateTime), NULL)
INSERT [dbo].[Company] ([companyId], [name], [phoneNumber], [email], [country], [website], [uuid], [externalId], [createdDate], [updateDate]) VALUES (19, N'tester', NULL, NULL, N'US', N'www.testco.com', N'385beb51-51ae-4ffe-8c05-3f35a9f99826', NULL, CAST(N'2017-07-15T19:13:09.833' AS DateTime), NULL)
INSERT [dbo].[Company] ([companyId], [name], [phoneNumber], [email], [country], [website], [uuid], [externalId], [createdDate], [updateDate]) VALUES (20, N'tester', NULL, NULL, N'US', N'www.testco.com', N'385beb51-51ae-4ffe-8c05-3f35a9f99827', NULL, CAST(N'2017-07-15T19:13:26.140' AS DateTime), NULL)
INSERT [dbo].[Company] ([companyId], [name], [phoneNumber], [email], [country], [website], [uuid], [externalId], [createdDate], [updateDate]) VALUES (21, N'tester', NULL, NULL, N'US', N'www.testco.com', N'385beb51-51ae-4ffe-8c05-3f35a9f99877', NULL, CAST(N'2017-07-15T19:13:37.593' AS DateTime), NULL)
INSERT [dbo].[Company] ([companyId], [name], [phoneNumber], [email], [country], [website], [uuid], [externalId], [createdDate], [updateDate]) VALUES (22, N'tester', NULL, NULL, N'US', N'www.testco.com', N'385beb51-51ae-4ffe-8c05-3f35a9f99848', NULL, CAST(N'2017-07-16T11:49:12.507' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Company] OFF
SET IDENTITY_INSERT [dbo].[Creator] ON 

INSERT [dbo].[Creator] ([creatorId], [firstName], [lastName], [fullName], [email], [language], [locale], [openId], [uuid], [createdDate], [updateDate]) VALUES (23, N'Test', N' User', NULL, N'testuser@testco.com', N'en', N'en-US', N'https://www.acme.com/openid/id/47cb8f55-1af6-5bfc-9a7d-8061d3aa0c97', N'47cb8f55-1af6-5bfc-9a7d-8061d3aa0c97', CAST(N'2017-07-15T15:37:52.253' AS DateTime), NULL)
INSERT [dbo].[Creator] ([creatorId], [firstName], [lastName], [fullName], [email], [language], [locale], [openId], [uuid], [createdDate], [updateDate]) VALUES (43, N'Test', N'User', NULL, N'testuser@testco.com', N'en', N'en-US', N'https://www.acme.com/openid/id/d124bf8b-0b0b-40d3-831b-b7f5a514d487', N'd124bf8b-0b0b-40d3-831b-b7f5a514d487', CAST(N'2017-07-15T20:16:50.710' AS DateTime), NULL)
INSERT [dbo].[Creator] ([creatorId], [firstName], [lastName], [fullName], [email], [language], [locale], [openId], [uuid], [createdDate], [updateDate]) VALUES (44, N'Test', N'User', NULL, N'testuser@testco.com', N'en', N'en-US', N'https://www.acme.com/openid/id/7f59aad1-85cd-4c04-b35b-906ee53acc71', N'7f59aad1-85cd-4c04-b35b-906ee53acc71', CAST(N'2017-07-15T22:58:37.890' AS DateTime), NULL)
INSERT [dbo].[Creator] ([creatorId], [firstName], [lastName], [fullName], [email], [language], [locale], [openId], [uuid], [createdDate], [updateDate]) VALUES (48, N'Another', N'User', NULL, N'c734676b-40f6-4783-b4ee-e20d59bbf943', N'en', N'en-US', N'https://www.acme.com/openid/id/7ac30510-c54c-45ca-9c2f-f4d6b3aa2c15', N'7ac30510-c54c-45ca-9c2f-f4d6b3aa2c15', CAST(N'2017-07-16T18:09:24.887' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Creator] OFF
SET IDENTITY_INSERT [dbo].[CreatorAddress] ON 

INSERT [dbo].[CreatorAddress] ([uniqueId], [creatorId], [street2], [state], [city], [country], [zip], [createdDate], [updatedDate], [street1]) VALUES (13, 43, NULL, N'MA', N'Sommerville', N'US', N'02144', CAST(N'2017-07-15T20:16:50.717' AS DateTime), NULL, N'55 Grove St')
INSERT [dbo].[CreatorAddress] ([uniqueId], [creatorId], [street2], [state], [city], [country], [zip], [createdDate], [updatedDate], [street1]) VALUES (14, 44, NULL, N'CA', N'San Jose', N'US', N'95131', CAST(N'2017-07-15T22:58:37.900' AS DateTime), NULL, N'1 Main St')
SET IDENTITY_INSERT [dbo].[CreatorAddress] OFF
SET IDENTITY_INSERT [dbo].[Edition] ON 

INSERT [dbo].[Edition] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (1, N'Stabdard', N'Stabdard', N'Standard edition', CAST(N'2017-07-12T23:37:20.040' AS DateTime), NULL)
INSERT [dbo].[Edition] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (2, N'DME', N'DME', N'???', CAST(N'2017-07-12T23:37:38.980' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Edition] OFF
SET IDENTITY_INSERT [dbo].[EventType] ON 

INSERT [dbo].[EventType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (1, N'USER_ASSIGNMENT', N'USER_ASSIGNMENT', N'User assignment order', CAST(N'2017-07-12T23:17:27.257' AS DateTime), NULL)
INSERT [dbo].[EventType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (2, N'USER_UNASSIGNMENT', N'USER_UNASSIGNMENT', N'User unassignment order', CAST(N'2017-07-12T23:17:49.280' AS DateTime), NULL)
INSERT [dbo].[EventType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (3, N'USER_UPDATED', N'USER_UPDATED', N'User update order', CAST(N'2017-07-12T23:18:10.267' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[EventType] OFF
SET IDENTITY_INSERT [dbo].[marketPlace] ON 

INSERT [dbo].[marketPlace] ([Id], [partnerName], [baseUrl], [description], [startDate], [endDate]) VALUES (1, N'APPDIRECT', N'https://www.acme.com', N'app direct marketplace ', CAST(N'2017-07-12T23:36:38.417' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[marketPlace] OFF
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (3, 1, CAST(N'2017-07-15T15:37:57.010' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), 23, 1, 18, N'Standard', N'MONTHLY')
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (4, 1, CAST(N'2017-07-15T19:13:09.847' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), 23, 1, 19, N'Standard', N'MONTHLY')
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (5, 1, CAST(N'2017-07-15T19:13:26.150' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), 23, 1, 20, N'Standard', N'MONTHLY')
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (6, 1, CAST(N'2017-07-15T19:13:37.597' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), 23, 1, 21, N'Standard', N'MONTHLY')
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (13, 3, CAST(N'2017-07-15T20:16:50.723' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), 43, 1, 21, NULL, NULL)
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (14, 3, CAST(N'2017-07-15T20:18:48.393' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), 43, 1, 19, NULL, NULL)
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (15, 2, CAST(N'2017-07-15T22:58:37.920' AS DateTime), CAST(N'2017-07-15T00:00:00.000' AS DateTime), 44, 1, 22, N'DME', N'DAILY')
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (16, 4, CAST(N'2017-07-16T11:41:22.197' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), NULL, 1, 21, NULL, NULL)
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (18, 4, CAST(N'2017-07-16T11:48:26.860' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), NULL, 1, 21, NULL, NULL)
INSERT [dbo].[Order] ([orderId], [orderTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [edition], [pricingDuration]) VALUES (19, 1, CAST(N'2017-07-16T11:49:12.513' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), 23, 1, 23, N'Standard', N'MONTHLY')
SET IDENTITY_INSERT [dbo].[Order] OFF
SET IDENTITY_INSERT [dbo].[OrderType] ON 

INSERT [dbo].[OrderType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (1, N'SUBSCRIPTION_ORDER', N'SUBSCRIPTION_ORDER', N'Subscription Order for creating subscription', CAST(N'2017-07-12T23:14:23.343' AS DateTime), NULL)
INSERT [dbo].[OrderType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (2, N'SUBSCRIPTION_CHANGE', N'SUBSCRIPTION_CHANGE', N'Subscription change request', CAST(N'2017-07-12T23:15:31.227' AS DateTime), NULL)
INSERT [dbo].[OrderType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (3, N'SUBSCRIPTION_CANCEL', N'SUBSCRIPTION_CANCEL', N'Subscription cancelled', CAST(N'2017-07-12T23:15:51.730' AS DateTime), NULL)
INSERT [dbo].[OrderType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (4, N'SUBSCRIPTION_NOTICE', N'SUBSCRIPTION_NOTICE', N'Subscription notice order', CAST(N'2017-07-12T23:16:50.500' AS DateTime), NULL)
INSERT [dbo].[OrderType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (5, N'USER_ASSIGNMENT', N'USER_ASSIGNMENT', N'User assignment order', CAST(N'2017-07-12T23:17:27.257' AS DateTime), NULL)
INSERT [dbo].[OrderType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (6, N'USER_UNASSIGNMENT', N'USER_UNASSIGNMENT', N'User unassignment order', CAST(N'2017-07-12T23:17:49.280' AS DateTime), NULL)
INSERT [dbo].[OrderType] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (7, N'USER_UPDATED', N'USER_UPDATED', N'User update order', CAST(N'2017-07-12T23:18:10.267' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[OrderType] OFF
SET IDENTITY_INSERT [dbo].[PricingDuration] ON 

INSERT [dbo].[PricingDuration] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (1, N'DAILY', N'DAILY', N'Daily pricing', CAST(N'2017-07-12T23:38:03.937' AS DateTime), NULL)
INSERT [dbo].[PricingDuration] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (2, N'MONTHLY', N'MONTHLY', N'Monthly pricing', CAST(N'2017-07-12T23:38:47.553' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[PricingDuration] OFF
SET IDENTITY_INSERT [dbo].[Status] ON 

INSERT [dbo].[Status] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (1, N'INITIALIZED', N'INITIALIZED', N'initialized', CAST(N'2017-07-12T23:08:11.477' AS DateTime), NULL)
INSERT [dbo].[Status] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (2, N'FAILED', N'FAILED', N'Failed', CAST(N'2017-07-12T23:08:59.730' AS DateTime), NULL)
INSERT [dbo].[Status] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (3, N'FREE_TRIAL', N'FREE_TRIAL', N'Free trial of application', CAST(N'2017-07-12T23:09:20.710' AS DateTime), NULL)
INSERT [dbo].[Status] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (4, N'FREE_TRIAL_EXPIRED', N'FREE_TRIAL_EXPIRED', N'Free trial expired', CAST(N'2017-07-12T23:09:33.920' AS DateTime), NULL)
INSERT [dbo].[Status] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (5, N'ACTIVE', N'ACTIVE', N'Active account', CAST(N'2017-07-12T23:10:37.420' AS DateTime), NULL)
INSERT [dbo].[Status] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (6, N'SUSPENDED', N'SUSPENDED', N'Suspended status of account', CAST(N'2017-07-12T23:11:05.363' AS DateTime), NULL)
INSERT [dbo].[Status] ([Id], [code], [shortDescription], [description], [startDate], [endDate]) VALUES (7, N'CANCELLED', N'CANCELLED', N'Cancelled account', CAST(N'2017-07-12T23:13:01.583' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Status] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([userId], [firstName], [lastName], [fullName], [email], [language], [locale], [openId], [uuid], [createdDate], [updateDate], [accountId], [statusId]) VALUES (2, N'Another', N'User', NULL, N'c734676b-40f6-4783-b4ee-e20d59bbf943', N'en', N'en-UK', N'https://www.acme.com/openid/id/7ac30510-c54c-45ca-9c2f-f4d6b3aa2c15', N'7ac30510-c54c-45ca-9c2f-f4d6b3aa2c15', CAST(N'2017-07-16T18:09:24.897' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), 23, 5)
INSERT [dbo].[User] ([userId], [firstName], [lastName], [fullName], [email], [language], [locale], [openId], [uuid], [createdDate], [updateDate], [accountId], [statusId]) VALUES (3, N'Another', N'User', NULL, N'c734676b-40f6-4783-b4ee-e20d59bbf943', N'en', N'en-US', N'https://www.acme.com/openid/id/7ac30510-c54c-45ca-9c2f-f4d6b3aa2c15', N'7ac30510-c54c-45ca-9c2f-f4d6b3aa2c16', CAST(N'2017-07-16T18:11:32.063' AS DateTime), NULL, 23, 5)
INSERT [dbo].[User] ([userId], [firstName], [lastName], [fullName], [email], [language], [locale], [openId], [uuid], [createdDate], [updateDate], [accountId], [statusId]) VALUES (5, N'Another', N'User', NULL, N'738a5bd7-7472-3ebb-9754-14520f89eac7', N'en', N'en-US', N'https://test.appdirect.com/openid/id/a9501ec3-d028-4ad0-a939-607d10c24fe3', N'a9501ec3-d028-4ad0-a939-607d10c24fe3', CAST(N'2017-07-16T18:35:43.423' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), 23, 7)
SET IDENTITY_INSERT [dbo].[User] OFF
SET IDENTITY_INSERT [dbo].[UserAddress] ON 

INSERT [dbo].[UserAddress] ([uniqueId], [userId], [street2], [state], [country], [zip], [createdDate], [updatedDate], [city], [street1]) VALUES (2, 5, NULL, N'CA', N'US', N'95131', CAST(N'2017-07-16T18:35:43.427' AS DateTime), NULL, N'San Jose', N'1 Main St')
SET IDENTITY_INSERT [dbo].[UserAddress] OFF
SET IDENTITY_INSERT [dbo].[UserEvent] ON 

INSERT [dbo].[UserEvent] ([eventId], [eventTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [userId]) VALUES (1, 1, CAST(N'2017-07-16T18:09:24.900' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), 48, 1, 23, 2)
INSERT [dbo].[UserEvent] ([eventId], [eventTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [userId]) VALUES (2, 1, CAST(N'2017-07-16T18:11:32.070' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), 48, 1, 23, 3)
INSERT [dbo].[UserEvent] ([eventId], [eventTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [userId]) VALUES (3, 1, CAST(N'2017-07-16T18:32:40.043' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), 48, 1, 23, 2)
INSERT [dbo].[UserEvent] ([eventId], [eventTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [userId]) VALUES (4, 1, CAST(N'2017-07-16T18:35:43.433' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), 48, 1, 23, 5)
INSERT [dbo].[UserEvent] ([eventId], [eventTypeId], [startDate], [completionDate], [creatorId], [marketPlaceId], [accountId], [userId]) VALUES (5, 1, CAST(N'2017-07-16T18:36:59.237' AS DateTime), CAST(N'2017-07-16T00:00:00.000' AS DateTime), 48, 1, 23, 5)
SET IDENTITY_INSERT [dbo].[UserEvent] OFF
ALTER TABLE [dbo].[Account] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[Company] ADD  DEFAULT (getdate()) FOR [createdDate]
GO
ALTER TABLE [dbo].[Creator] ADD  DEFAULT (getdate()) FOR [createdDate]
GO
ALTER TABLE [dbo].[CreatorAddress] ADD  DEFAULT (getdate()) FOR [createdDate]
GO
ALTER TABLE [dbo].[Edition] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[EventType] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[marketPlace] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[Order] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[OrderType] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[PricingDuration] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[Status] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT (getdate()) FOR [createdDate]
GO
ALTER TABLE [dbo].[UserAddress] ADD  DEFAULT (getdate()) FOR [createdDate]
GO
ALTER TABLE [dbo].[UserEvent] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [Account_Company_FK] FOREIGN KEY([companyId])
REFERENCES [dbo].[Company] ([companyId])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [Account_Company_FK]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [account_Status_FK] FOREIGN KEY([statusId])
REFERENCES [dbo].[Status] ([Id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [account_Status_FK]
GO
ALTER TABLE [dbo].[CreatorAddress]  WITH CHECK ADD  CONSTRAINT [CreatorAddress_Creator_FK] FOREIGN KEY([creatorId])
REFERENCES [dbo].[Creator] ([creatorId])
GO
ALTER TABLE [dbo].[CreatorAddress] CHECK CONSTRAINT [CreatorAddress_Creator_FK]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [Order_Account_FK] FOREIGN KEY([accountId])
REFERENCES [dbo].[Account] ([accountId])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [Order_Account_FK]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [Order_Creator_FK] FOREIGN KEY([creatorId])
REFERENCES [dbo].[Creator] ([creatorId])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [Order_Creator_FK]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [Order_marketPlace_FK] FOREIGN KEY([marketPlaceId])
REFERENCES [dbo].[marketPlace] ([Id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [Order_marketPlace_FK]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [Order_OrderType_FK] FOREIGN KEY([orderTypeId])
REFERENCES [dbo].[OrderType] ([Id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [Order_OrderType_FK]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [User_Account_FK] FOREIGN KEY([accountId])
REFERENCES [dbo].[Account] ([accountId])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [User_Account_FK]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [User_Status_FK] FOREIGN KEY([statusId])
REFERENCES [dbo].[Status] ([Id])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [User_Status_FK]
GO
ALTER TABLE [dbo].[UserAddress]  WITH CHECK ADD  CONSTRAINT [UserAddress_User_FK] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([userId])
GO
ALTER TABLE [dbo].[UserAddress] CHECK CONSTRAINT [UserAddress_User_FK]
GO
ALTER TABLE [dbo].[UserEvent]  WITH CHECK ADD  CONSTRAINT [UserEvent_Account_FK] FOREIGN KEY([accountId])
REFERENCES [dbo].[Account] ([accountId])
GO
ALTER TABLE [dbo].[UserEvent] CHECK CONSTRAINT [UserEvent_Account_FK]
GO
ALTER TABLE [dbo].[UserEvent]  WITH CHECK ADD  CONSTRAINT [UserEvent_Creator_FK] FOREIGN KEY([creatorId])
REFERENCES [dbo].[Creator] ([creatorId])
GO
ALTER TABLE [dbo].[UserEvent] CHECK CONSTRAINT [UserEvent_Creator_FK]
GO
ALTER TABLE [dbo].[UserEvent]  WITH CHECK ADD  CONSTRAINT [UserEvent_EventType_FK] FOREIGN KEY([eventTypeId])
REFERENCES [dbo].[EventType] ([Id])
GO
ALTER TABLE [dbo].[UserEvent] CHECK CONSTRAINT [UserEvent_EventType_FK]
GO
ALTER TABLE [dbo].[UserEvent]  WITH CHECK ADD  CONSTRAINT [UserEvent_marketPlace_FK] FOREIGN KEY([marketPlaceId])
REFERENCES [dbo].[marketPlace] ([Id])
GO
ALTER TABLE [dbo].[UserEvent] CHECK CONSTRAINT [UserEvent_marketPlace_FK]
GO
ALTER TABLE [dbo].[UserEvent]  WITH CHECK ADD  CONSTRAINT [UserEvent_User_FK] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([userId])
GO
ALTER TABLE [dbo].[UserEvent] CHECK CONSTRAINT [UserEvent_User_FK]
GO
USE [master]
GO
ALTER DATABASE [appData] SET  READ_WRITE 
GO
