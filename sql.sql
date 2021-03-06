USE [master]
GO
/****** Object:  Database [EduSys]    Script Date: 10/13/2021 4:04:56 PM ******/
CREATE DATABASE [EduSys]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'EduSys', FILENAME = N'D:\document\MSSQL15.SQLEXPRESS\MSSQL\DATA\EduSys.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'EduSys_log', FILENAME = N'D:\document\MSSQL15.SQLEXPRESS\MSSQL\DATA\EduSys_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [EduSys] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [EduSys].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [EduSys] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [EduSys] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [EduSys] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [EduSys] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [EduSys] SET ARITHABORT OFF 
GO
ALTER DATABASE [EduSys] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [EduSys] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [EduSys] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [EduSys] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [EduSys] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [EduSys] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [EduSys] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [EduSys] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [EduSys] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [EduSys] SET  ENABLE_BROKER 
GO
ALTER DATABASE [EduSys] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [EduSys] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [EduSys] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [EduSys] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [EduSys] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [EduSys] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [EduSys] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [EduSys] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [EduSys] SET  MULTI_USER 
GO
ALTER DATABASE [EduSys] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [EduSys] SET DB_CHAINING OFF 
GO
ALTER DATABASE [EduSys] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [EduSys] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [EduSys] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [EduSys] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [EduSys] SET QUERY_STORE = OFF
GO
USE [EduSys]
GO
/****** Object:  Table [dbo].[ChuyenDe]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChuyenDe](
	[MaCD] [nchar](5) NOT NULL,
	[TenCD] [nvarchar](50) NOT NULL,
	[HocPhi] [float] NOT NULL,
	[ThoiLuong] [int] NOT NULL,
	[hinh] [nvarchar](50) NOT NULL,
	[MoTa] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HocVien]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HocVien](
	[MaHV] [int] IDENTITY(1,1) NOT NULL,
	[MaKH] [int] NOT NULL,
	[MaNH] [nchar](7) NOT NULL,
	[Diem] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhoaHoc]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhoaHoc](
	[MaKH] [int] IDENTITY(1,1) NOT NULL,
	[MaCD] [nchar](5) NOT NULL,
	[HocPhi] [float] NOT NULL,
	[ThoiLuong] [int] NOT NULL,
	[NgayKG] [date] NOT NULL,
	[GhiChu] [nvarchar](255) NULL,
	[MaNV] [nvarchar](20) NOT NULL,
	[NgayTao] [date] NOT NULL,
 CONSTRAINT [PK__KhoaHoc__2725CF1E87EF56B7] PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NguoiHoc]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NguoiHoc](
	[MaNH] [nchar](7) NOT NULL,
	[HoTen] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NULL,
	[NgaySinh] [date] NOT NULL,
	[DienThoai] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[ghiChu] [nvarchar](255) NULL,
	[MaNV] [nvarchar](20) NULL,
	[NgayDK] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [nvarchar](20) NOT NULL,
	[MatKhau] [nvarchar](50) NOT NULL,
	[HoTen] [nvarchar](50) NOT NULL,
	[VaiTro] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[HocVien] ADD  CONSTRAINT [DF__HocVien__Diem__32E0915F]  DEFAULT ((-1)) FOR [Diem]
GO
ALTER TABLE [dbo].[KhoaHoc] ADD  CONSTRAINT [DF__KhoaHoc__NgayTao__29572725]  DEFAULT (getdate()) FOR [NgayTao]
GO
ALTER TABLE [dbo].[NguoiHoc] ADD  DEFAULT ((1)) FOR [GioiTinh]
GO
ALTER TABLE [dbo].[NguoiHoc] ADD  DEFAULT (getdate()) FOR [NgayDK]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [VaiTro]
GO
ALTER TABLE [dbo].[HocVien]  WITH CHECK ADD  CONSTRAINT [FK__HocVien__MaKH__33D4B598] FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhoaHoc] ([MaKH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HocVien] CHECK CONSTRAINT [FK__HocVien__MaKH__33D4B598]
GO
ALTER TABLE [dbo].[HocVien]  WITH CHECK ADD FOREIGN KEY([MaNH])
REFERENCES [dbo].[NguoiHoc] ([MaNH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[KhoaHoc]  WITH CHECK ADD  CONSTRAINT [FK__KhoaHoc__MaCD__2A4B4B5E] FOREIGN KEY([MaCD])
REFERENCES [dbo].[ChuyenDe] ([MaCD])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[KhoaHoc] CHECK CONSTRAINT [FK__KhoaHoc__MaCD__2A4B4B5E]
GO
ALTER TABLE [dbo].[KhoaHoc]  WITH CHECK ADD  CONSTRAINT [FK__KhoaHoc__MaNV__2B3F6F97] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[KhoaHoc] CHECK CONSTRAINT [FK__KhoaHoc__MaNV__2B3F6F97]
GO
ALTER TABLE [dbo].[NguoiHoc]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
/****** Object:  StoredProcedure [dbo].[sp_BangDiem]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_BangDiem](@MaKH int)
as
begin
   select 
         HocVien.MaNH,
		 HoTen,
		 Diem
   from HocVien join NguoiHoc on HocVien.MaNH = NguoiHoc.MaNH
   where HocVien.MaKH  = @MaKH
   order by Diem desc
end
GO
/****** Object:  StoredProcedure [dbo].[sp_thongkeDiem]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_thongkeDiem] 
as
begin
    select 
	     TenCD,
		 COUNT(MaHV) as SoHV,
		 round(MIN(Diem),2) as ThapNhat,
		 round(MAX(Diem),2) as CaoNhat,
		 round(AVG(Diem),2) as TrungBinh

	from KhoaHoc  join ChuyenDe on KhoaHoc.MaCD = ChuyenDe.MaCD
	              join HocVien on KhoaHoc.MaKH = HocVien.MaKH
    group by TenCD
end
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeDoanhThu]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_ThongKeDoanhThu](@year int)
as begin
  select 
       TenCD ChuyenDe,
	   COUNT(distinct KhoaHoc.MaKH) soKH,
	   COUNT(HocVien.MaHV) soHV,
	   sum(KhoaHoc.HocPhi) HocPhi,
	   min(KhoaHoc.HocPhi) ThapNhat,
	   max(KhoaHoc.HocPhi) CaoNhat,
	   AVG(KhoaHoc.HocPhi) TrungBinh
  from KhoaHoc join HocVien on KhoaHoc.MaKH = HocVien.MaKH
               join ChuyenDe on ChuyenDe.MaCD = KhoaHoc.MaCD
  where YEAR(NgayKG) = @year
  group by TenCD
end
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeNguoiHoc]    Script Date: 10/13/2021 4:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_ThongKeNguoiHoc]
as
begin
 select 
      YEAR(NgayDK) Nam,
	  COUNT(MaNH) SoNH,
	  min(NgayDK) DauTien,
	  MAX(NgayDK) CuoiCung
 from NguoiHoc 
 Group by YEAR(NgayDK)
end
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã chuyên đề, PK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ChuyenDe', @level2type=N'COLUMN',@level2name=N'MaCD'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên chuyên đề, Unique' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ChuyenDe', @level2type=N'COLUMN',@level2name=N'TenCD'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Học phí' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ChuyenDe', @level2type=N'COLUMN',@level2name=N'HocPhi'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Thời lượng (ngày)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ChuyenDe', @level2type=N'COLUMN',@level2name=N'ThoiLuong'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Hình logo' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ChuyenDe', @level2type=N'COLUMN',@level2name=N'hinh'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mô tả chuyên đề' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ChuyenDe', @level2type=N'COLUMN',@level2name=N'MoTa'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã học viên, PK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'HocVien', @level2type=N'COLUMN',@level2name=N'MaHV'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã khóa học, FK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'HocVien', @level2type=N'COLUMN',@level2name=N'MaKH'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã người học, FK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'HocVien', @level2type=N'COLUMN',@level2name=N'MaNH'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Điểm' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'HocVien', @level2type=N'COLUMN',@level2name=N'Diem'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã khách hàng, PK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'KhoaHoc', @level2type=N'COLUMN',@level2name=N'MaKH'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã chuyên đề, FK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'KhoaHoc', @level2type=N'COLUMN',@level2name=N'MaCD'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Học phí' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'KhoaHoc', @level2type=N'COLUMN',@level2name=N'HocPhi'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Thời lượng (ngày)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'KhoaHoc', @level2type=N'COLUMN',@level2name=N'ThoiLuong'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày khai giảng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'KhoaHoc', @level2type=N'COLUMN',@level2name=N'NgayKG'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ghi chú' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'KhoaHoc', @level2type=N'COLUMN',@level2name=N'GhiChu'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã người tạo, FK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'KhoaHoc', @level2type=N'COLUMN',@level2name=N'MaNV'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày tạo' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'KhoaHoc', @level2type=N'COLUMN',@level2name=N'NgayTao'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã người học, PK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'MaNH'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Họ và tên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'HoTen'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Giới tính' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'GioiTinh'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày sinh' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'NgaySinh'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Điện thoại' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'DienThoai'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Địa chỉ email' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'Email'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ghi chú' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'ghiChu'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã người tạo, FK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'MaNV'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày tạo' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NguoiHoc', @level2type=N'COLUMN',@level2name=N'NgayDK'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã nhân viên, PK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NhanVien', @level2type=N'COLUMN',@level2name=N'MaNV'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mật khẩu' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NhanVien', @level2type=N'COLUMN',@level2name=N'MatKhau'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Họ và tên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NhanVien', @level2type=N'COLUMN',@level2name=N'HoTen'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Vai trò, 1-trưởng phòng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NhanVien', @level2type=N'COLUMN',@level2name=N'VaiTro'
GO
USE [master]
GO
ALTER DATABASE [EduSys] SET  READ_WRITE 
GO
