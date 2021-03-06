USE [master]
GO
/****** Object:  Database [Assignment3_TranQuangVu]    Script Date: 3/6/2021 12:26:53 AM ******/
CREATE DATABASE [Assignment3_TranQuangVu]
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET ARITHABORT OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET  MULTI_USER 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET QUERY_STORE = OFF
GO
USE [Assignment3_TranQuangVu]
GO
/****** Object:  Table [dbo].[tblCars]    Script Date: 3/6/2021 12:26:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCars](
	[carID] [varchar](50) NOT NULL,
	[carBrand] [varchar](50) NOT NULL,
	[carName] [varchar](50) NOT NULL,
	[color] [varchar](50) NOT NULL,
	[slot] [int] NOT NULL,
	[image] [varchar](300) NOT NULL,
	[price] [float] NOT NULL,
	[categoryID] [varchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
 CONSTRAINT [PK_tblCars] PRIMARY KEY CLUSTERED 
(
	[carID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 3/6/2021 12:26:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [varchar](50) NOT NULL,
	[categoryName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tblCategory] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 3/6/2021 12:26:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[discountCode] [varchar](50) NOT NULL,
	[userID] [varchar](50) NOT NULL,
	[carBrand] [varchar](50) NOT NULL,
	[discount] [int] NOT NULL,
	[startDay] [datetime] NOT NULL,
	[endDay] [datetime] NOT NULL,
 CONSTRAINT [PK_tblDiscount] PRIMARY KEY CLUSTERED 
(
	[discountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblFeedback]    Script Date: 3/6/2021 12:26:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblFeedback](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](50) NOT NULL,
	[detailID] [int] NOT NULL,
	[content] [varchar](200) NOT NULL,
	[rating] [int] NOT NULL,
 CONSTRAINT [PK_tblFeedback] PRIMARY KEY CLUSTERED 
(
	[feedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRent]    Script Date: 3/6/2021 12:26:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRent](
	[rentID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](50) NOT NULL,
	[totalPrice] [float] NOT NULL,
	[createDate] [date] NOT NULL,
	[status] [varchar](50) NOT NULL,
	[renter] [varchar](50) NULL,
	[phoneNumber] [varchar](15) NULL,
	[email] [varchar](50) NULL,
 CONSTRAINT [PK_tblRent] PRIMARY KEY CLUSTERED 
(
	[rentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRentDetail]    Script Date: 3/6/2021 12:26:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRentDetail](
	[detailID] [int] IDENTITY(1,1) NOT NULL,
	[rentID] [int] NOT NULL,
	[carID] [varchar](50) NOT NULL,
	[carBrand] [varchar](50) NOT NULL,
	[carName] [varchar](50) NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[startDay] [date] NOT NULL,
	[endDay] [date] NOT NULL,
	[discountCode] [varchar](50) NULL,
 CONSTRAINT [PK_tblRentDetail] PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 3/6/2021 12:26:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [varchar](50) NOT NULL,
	[roleName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tblRoles] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 3/6/2021 12:26:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](50) NOT NULL,
	[fullName] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[roleID] [varchar](50) NOT NULL,
	[phoneNumber] [varchar](15) NULL,
	[email] [varchar](50) NULL,
	[address] [varchar](50) NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'AUD01', N'AUDI', N'R8', N'White', 2, N'https://ae01.alicdn.com/kf/H07bf8ae3839c4df1ae1a0d01e858af2c8/1-24-Audi-R8-Original-Pull-Back-Diecast-Metal-Car-Model-Toy-High-Simulation-Sound-and.jpg', 7000, N'COUPE', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'AUD02', N'AUDI', N'Q8', N'Green', 5, N'https://360view.hum3d.com/zoom/Audi/Audi_Q8_RS_2020_1000_0001.jpg', 9000, N'SUV', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'AUD03', N'AUDI', N'Q8', N'Orange', 5, N'https://360view.hum3d.com/zoom/Audi/Audi_Q8_S_line_2018_1000_0001.jpg', 9000, N'SUV', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'AUD04', N'AUDI', N'TT', N'Red', 2, N'https://360view.hum3d.com/zoom/Audi/Audi_TT_Mk3_8S_S_coupe_2015_1000_0001.jpg', 6000, N'COUPE', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'AUD05', N'AUDI', N'A6', N'Black', 4, N'https://pict1.reezocar.com/images/autoscout24.it/RZCATSIT0C91EB080995/AUDI-A6-00.jpg', 4000, N'SEDAN', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'BMW01', N'BMW', N'M3', N'White', 4, N'https://s1.cdn.autoevolution.com/images/news/new-2021-bmw-m3-rendered-based-on-leaked-photos-massive-grille-is-here-141150_1.jpg', 1000, N'SPORT', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'BMW02', N'BMW', N'X3', N'Blue', 5, N'https://www.bmw.vn/content/bmw/marketASIA/bmw_vn/vi_VN/all-models/x-series/X3/2019/bmw-x3-inspire/jcr:content/par/highlight_7770/highlightitems/highlightitem_45f3/image/mobile.transform/highlight/image.1565163030175.jpg', 1500, N'SUV', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'BMW03', N'BMW', N'X6', N'Brown', 5, N'https://ymimg1.b8cdn.com/resized/used_car/2020/3/16/990154/pictures/5233595/listing_main_image_0.jpg', 3000, N'SUV', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'BMW04', N'BMW', N'Z4', N'Silver', 2, N'https://i.pinimg.com/originals/7a/6f/51/7a6f514e7d405cd82c54821b90b843fc.jpg', 5000, N'COUPE', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'BMW05', N'BMW', N'i8', N'White', 2, N'https://cdn.shopify.com/s/files/1/0109/7580/5521/products/bmw_i8_2014_ZEN-Rage_Valvetronic_exhaust_system_1.5T_1000x.jpg?v=1574628747', 10000, N'COUPE', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'FER01', N'FERRARI', N'488', N'Silver', 2, N'https://i.pinimg.com/originals/d2/cf/b6/d2cfb69997fc29dba99109fbeda6ff6d.jpg', 50000, N'COUPE', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'FER02', N'FERRARI', N'F8', N'Red', 2, N'https://d2snyq93qb0udd.cloudfront.net/prod/25196_social-ferrari-f8-tributo.jpg', 40000, N'COUPE', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'FORD1', N'FORD', N'MUSTANG', N'White & Black', 2, N'https://images.fitmentindustries.com/web/753442-2-2017-mustang-ford-gt-steeda-lowering-springs-xxr-570-gold.jpg', 13000, N'SPORT', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'JAG01', N'JAGUAR', N'F-PACE', N'Black', 5, N'https://cdn.shopify.com/s/files/1/0048/2348/2461/products/50jddc975bkw_1_2048x2048.jpg?v=1574005298', 20000, N'SUV', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'JAG02', N'JAGUAR', N'XJ', N'Black', 4, N'https://cdn-jaguarlandrover.com/api/v1//image/13553', 10000, N'SEDAN', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'LAM01', N'LAMBORGHINI', N'HURACAN', N'Black', 2, N'https://i.pinimg.com/originals/68/6d/ae/686dae44eb0a23492b9303f80dd9390f.jpg', 80000, N'SPORT', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'LAM02', N'LAMBORGHINI', N'AVENTADOR', N'Pink', 2, N'https://tapchixe360.com/wp-content/uploads/2020/11/1604764632_123463985-1745653688928287-3879567319857770431-o-6a9b.jpg', 100000, N'SPORT', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'LEX01', N'LEXUS', N'LX570', N'White', 5, N'https://img.tinxe.vn/resize/1000x-/2020/09/25/uU7VvIGZ/lexus-lx570-2020-47c3.jpg', 15000, N'SUV', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'MER01', N'MERCEDES', N'S650', N'Black', 4, N'https://tapchisieuxe.com/uploads/images/bai-viet/thuong-hieu/mercedes/maybach-s650-thu-2/dai-gia-ha-nam-cung-vua-moi-tau-mercedes-maybach-s650-2019-gan-15-ty-kip-don-tet-3.jpg', 14000, N'SEDAN', 5)
INSERT [dbo].[tblCars] ([carID], [carBrand], [carName], [color], [slot], [image], [price], [categoryID], [quantity]) VALUES (N'VOL01', N'VOLVO', N'S60', N'White', 4, N'https://i.pinimg.com/originals/51/2e/ff/512effe79bba373c4950718677d852ef.png', 9000, N'SEDAN', 5)
GO
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'COUPE', N'Coupe')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'HATCHBACK', N'Hatchback')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'PICKUP', N'Pickup Truck')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'SEDAN', N'Sedan')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'SPORT', N'Sport')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'SUV', N'Sport Utility Vehicle')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'VAN', N'Van')
GO
INSERT [dbo].[tblDiscount] ([discountCode], [userID], [carBrand], [discount], [startDay], [endDay]) VALUES (N'', N'admin', N' ', 0, CAST(N'2021-02-25T00:00:00.000' AS DateTime), CAST(N'2022-02-25T00:00:00.000' AS DateTime))
INSERT [dbo].[tblDiscount] ([discountCode], [userID], [carBrand], [discount], [startDay], [endDay]) VALUES (N'THANTAIDEN', N'admin', N'BMW', 10, CAST(N'2021-02-25T00:00:00.000' AS DateTime), CAST(N'2021-03-25T00:00:00.000' AS DateTime))
INSERT [dbo].[tblDiscount] ([discountCode], [userID], [carBrand], [discount], [startDay], [endDay]) VALUES (N'XUAN2021', N'admin', N'Lexus', 15, CAST(N'2021-02-25T00:00:00.000' AS DateTime), CAST(N'2021-03-25T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (N'ad', N'Administrator')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (N'us', N'User')
GO
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [phoneNumber], [email], [address], [status]) VALUES (N'admin', N'admin', N'1', N'ad', N'02589631474', N'xyzt@gmail.com', NULL, 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [phoneNumber], [email], [address], [status]) VALUES (N'user', N'user', N'1', N'us', N'01234567894', N'abcd@gmail.com', NULL, 1)
GO
ALTER TABLE [dbo].[tblCars]  WITH CHECK ADD  CONSTRAINT [FK_tblCars_tblCategory] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblCars] CHECK CONSTRAINT [FK_tblCars_tblCategory]
GO
ALTER TABLE [dbo].[tblDiscount]  WITH CHECK ADD  CONSTRAINT [FK_tblDiscount_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblDiscount] CHECK CONSTRAINT [FK_tblDiscount_tblUsers]
GO
ALTER TABLE [dbo].[tblFeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblFeedback_tblRentDetail] FOREIGN KEY([detailID])
REFERENCES [dbo].[tblRentDetail] ([detailID])
GO
ALTER TABLE [dbo].[tblFeedback] CHECK CONSTRAINT [FK_tblFeedback_tblRentDetail]
GO
ALTER TABLE [dbo].[tblFeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblFeedback_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblFeedback] CHECK CONSTRAINT [FK_tblFeedback_tblUsers]
GO
ALTER TABLE [dbo].[tblRent]  WITH CHECK ADD  CONSTRAINT [FK_tblRent_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblRent] CHECK CONSTRAINT [FK_tblRent_tblUsers]
GO
ALTER TABLE [dbo].[tblRentDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblRentDetail_tblCars] FOREIGN KEY([carID])
REFERENCES [dbo].[tblCars] ([carID])
GO
ALTER TABLE [dbo].[tblRentDetail] CHECK CONSTRAINT [FK_tblRentDetail_tblCars]
GO
ALTER TABLE [dbo].[tblRentDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblRentDetail_tblDiscount] FOREIGN KEY([discountCode])
REFERENCES [dbo].[tblDiscount] ([discountCode])
GO
ALTER TABLE [dbo].[tblRentDetail] CHECK CONSTRAINT [FK_tblRentDetail_tblDiscount]
GO
ALTER TABLE [dbo].[tblRentDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblRentDetail_tblRent] FOREIGN KEY([rentID])
REFERENCES [dbo].[tblRent] ([rentID])
GO
ALTER TABLE [dbo].[tblRentDetail] CHECK CONSTRAINT [FK_tblRentDetail_tblRent]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_tblUsers_tblRoles] FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_tblUsers_tblRoles]
GO
USE [master]
GO
ALTER DATABASE [Assignment3_TranQuangVu] SET  READ_WRITE 
GO
