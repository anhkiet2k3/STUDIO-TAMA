-- Tạo database

CREATE DATABASE DATN_ASM
GO
USE DATN_ASM

GO
-- Tạo bảng tài khoản
CREATE TABLE TAIKHOAN (
	tentaikhoan NVARCHAR(50) NOT NULL PRIMARY KEY,
	matkhau VARCHAR(Max) NOT NULL,
	ten NVARCHAR(50) NULL,
	sdt VARCHAR(20)  NULL,
	diachi NVARCHAR(max) NULL,
	hinhanh VARCHAR(Max) NOT NULL,
	email VARCHAR(50) NULL,
)
GO
-- Tạo bảng vai trò
create table VAITRO (
     id nvarchar(50)  NOT NULL PRIMARY KEY,
	 ten varchar(50) not null
)
GO
-- Tạo bảng quyền
create table QUYEN (
    id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	tentaikhoan NVARCHAR(50) NOT NULL,
	vaitroID   nvarchar(50) NOT NULL
	FOREIGN KEY (tentaikhoan) REFERENCES TAIKHOAN(tentaikhoan),
	FOREIGN KEY (vaitroID) REFERENCES VAITRO(id)
)
GO
-- Tạo bảng loai
CREATE TABLE LOAI (
	id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	ten NVARCHAR(50) NOT NULL,
)
GO
-- Tạo bảng le phuc
CREATE TABLE LEPHUC (
  id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  ten NVARCHAR(max) NOT NULL,
  mota NVARCHAR(max) NOT NULL,
  noidung NVARCHAR(max) NOT NULL,
  gia int NOT NULL,
  hinh NVARCHAR(max) NULL,
  idloai int NOT NULL,
  FOREIGN KEY (idloai) REFERENCES LOAI(id)
)
GO
-- Tạo bảng binh luan le phuc
CREATE TABLE BINHLUANLEPHUC (
  id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  noidung NVARCHAR(max) NOT NULL,
  tentaikhoan NVARCHAR(50) NOT NULL,
  idlephuc int NULL,
  FOREIGN KEY (tentaikhoan) REFERENCES TAIKHOAN(tentaikhoan),
  FOREIGN KEY (idlephuc) REFERENCES LEPHUC(id)
)
GO
-- Tạo bảng donhang
CREATE TABLE DONHANG (
  id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  tentaikhoan NVARCHAR(50)NOT NULL,
  ngaytao DATETIME NOT NULL,
  tongtien int NOT NULL,
  diachi NVARCHAR(MAX) NULL,
  trangthai int NOT NULL,
  cachnhan bit NOT NULL,
  hinhthuc bit NOT NULL,
  FOREIGN KEY (tentaikhoan) REFERENCES TAIKHOAN(tentaikhoan)
)
GO
-- Tạo bảng chi tiết đơn hàng
CREATE TABLE CHITIETDONHANG (
	id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	iddonhang INT NOT NULL,
	idlephuc INT NOT NULL,
	soluong INT NOT NULL,
	gia int NOT NULL,
	--PRIMARY KEY (iddonhang, idsanpham)
	FOREIGN KEY (iddonhang) REFERENCES DONHANG(id),
	FOREIGN KEY (idlephuc) REFERENCES LEPHUC(id)
)
GO
-- Tạo bảng dich vu
CREATE TABLE DICHVU (
  id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  ten NVARCHAR(max) NOT NULL,
  gia int NOT NULL,
  noidung NVARCHAR(max) NOT NULL,
  mota NVARCHAR(max) NOT NULL,
  hinhanh1 VARCHAR(255) NOT NULL,
  hinhanh2 VARCHAR(255) NULL,
  href VARCHAR(50) NULL,
)
GO
-- Tạo bảng dat lich
CREATE TABLE DATLICH (
	id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	tentaikhoan NVARCHAR(50)NOT NULL,
	iddichvu INT NOT NULL,
	ngaythue DATE NOT NULL,
	thoigian DATETIME NOT NULL,
	diachi NVARCHAR(MAX) NULL,
	ghichu NVARCHAR(MAX)  NULL,
	cachnhan bit NOT NULL,
	trangthai int NOT NULL,
	FOREIGN KEY (tentaikhoan) REFERENCES TAIKHOAN(tentaikhoan),
	FOREIGN KEY (iddichvu) REFERENCES DICHVU(id)
)
GO
-- Tạo bảng bài viết
CREATE TABLE BAIVIET (
  id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  tieude NVARCHAR(500) NOT NULL,
  noidung NVARCHAR(1000) NOT NULL,
  ngaydang date DEFAULT GETDATE(),
  hinhanh VARCHAR(255) NULL,
)
GO
-- Tạo bảng bình luận bài viết
CREATE TABLE BINHLUANBAIVIET (
  id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  noidung NVARCHAR(max) NOT NULL,
  tentaikhoan NVARCHAR(50) NOT NULL,
  idbaiviet int NULL,
  FOREIGN KEY (tentaikhoan) REFERENCES TAIKHOAN(tentaikhoan),
  FOREIGN KEY (idbaiviet) REFERENCES BAIVIET(id)
)
GO
-- Tạo bảng giỏ hàng 
CREATE TABLE GIOHANG (
  id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  soluong int NULL,
  tentaikhoan NVARCHAR(50) NOT NULL,
  idlephuc int NULL,
  FOREIGN KEY (tentaikhoan) REFERENCES TAIKHOAN(tentaikhoan),
  FOREIGN KEY (idlephuc) REFERENCES LEPHUC(id),
)
GO

-- Trg Xóa chi tiết đơn hàng
GO
CREATE TRIGGER Trg_Xoa_DonHang
ON DONHANG
INSTEAD OF DELETE
AS 
BEGIN
	DELETE FROM CHITIETDONHANG
    WHERE iddonhang IN (SELECT id FROM deleted);
    
	DELETE FROM DONHANG
    WHERE id IN (SELECT id FROM deleted);
END
GO
-- Thêm dữ liệu --
INSERT [dbo].[TAIKHOAN] ([tentaikhoan], [matkhau], [ten], [sdt], [diachi], [email], [hinhanh]) VALUES (N'admin', N'$2a$12$TzGGoUtHGOyHffIOUsX/7.dRUNl30VF0TUxC9MyJ2wpcRNZPKAyuy', N'Nguyễn Văn Ba', N'0397420764', N'Quận 12 , TP HCM', N'doletri147@gmail.com', N'anh1.jpeg')
GO
INSERT [dbo].[TAIKHOAN] ([tentaikhoan], [matkhau], [ten], [sdt], [diachi], [email], [hinhanh]) VALUES (N'admin12', N'$2a$10$t5g1K3Q0WG5cHX2ctXq0peyXAMYHwEoCEnzNAiMq29Gv4x5HpXAvK', N'Đỗ Lê Trí', N'09129834846', N'Quận 12 , TP HCM', N'tridlps23150@fpt.edu.vn', N'avatar1.webp')
GO
INSERT [dbo].[TAIKHOAN] ([tentaikhoan], [matkhau], [ten], [sdt], [diachi], [email], [hinhanh]) VALUES (N'tritri1', N'$2a$10$fcuETghrXYMkVliDhsPPtuSeTQcpBgzKOf5jMdUUsQP9j9.7e5KQC', N'Đỗ Lê Trí', N'123456', N'Quận 12 , TP HCM', N'tridlps23150@fpt.edu.vn', N'anh1.jpeg')
GO
INSERT [dbo].[TAIKHOAN] ([tentaikhoan], [matkhau], [ten], [sdt], [diachi], [email], [hinhanh]) VALUES (N'user1', N'$2a$10$.d7DEcqvz4TupST5MydBH.4Slj.O8.H3v0XZAqt2Y6hCRP/R6iTeG', N'Nguyễn Trí', N'0987654321', N'quan12', N'tri@gmail.com', N'avatar1.webp')
GO
INSERT [dbo].[TAIKHOAN] ([tentaikhoan], [matkhau], [ten], [sdt], [diachi], [email], [hinhanh]) VALUES (N'user2', N'$2a$10$gIO6iu4BG6qmxQd1iecAyuELBJ7n1sc/DhRY8RpnudgWBVkWI7u5K', N'Kiệt Thấp', N'0987654321', N'Tô Ký', N'kiet@gmail.com', N'avatar1.webp')
GO
INSERT [dbo].[TAIKHOAN] ([tentaikhoan], [matkhau], [ten], [sdt], [diachi], [email], [hinhanh]) VALUES (N'user3', N'$2a$10$KedGr2/G87F6llvjBy7h6erJhIZhFVO0ATnzzNmyyFvFJ7L6YkVkq', N'Bảo Đậu', N'0987654321', N'Quận 10', N'bao@gmail.com', N'avatar1.webp')
GO
INSERT [dbo].[VAITRO] ([id], [ten]) VALUES (N'ADMIN', N'ADMIN')
GO
INSERT [dbo].[VAITRO] ([id], [ten]) VALUES (N'USER', N'USER')
GO
SET IDENTITY_INSERT [dbo].[QUYEN] ON 
GO
INSERT [dbo].[QUYEN] ([id], [tentaikhoan], [vaitroID]) VALUES (1, N'admin', N'ADMIN')
GO
INSERT [dbo].[QUYEN] ([id], [tentaikhoan], [vaitroID]) VALUES (6, N'user1', N'USER')
GO
INSERT [dbo].[QUYEN] ([id], [tentaikhoan], [vaitroID]) VALUES (7, N'user2', N'USER')
GO
INSERT [dbo].[QUYEN] ([id], [tentaikhoan], [vaitroID]) VALUES (8, N'user3', N'USER')
GO
INSERT [dbo].[QUYEN] ([id], [tentaikhoan], [vaitroID]) VALUES (9, N'admin12', N'USER')
GO
INSERT [dbo].[QUYEN] ([id], [tentaikhoan], [vaitroID]) VALUES (10, N'tritri1', N'USER')
GO
SET IDENTITY_INSERT [dbo].[QUYEN] OFF
GO
SET IDENTITY_INSERT [dbo].[LOAI] ON 
GO
INSERT [dbo].[LOAI] ([id], [ten]) VALUES (1, N'Váy Cưới')
GO
INSERT [dbo].[LOAI] ([id], [ten]) VALUES (2, N'Áo Dài')
GO
INSERT [dbo].[LOAI] ([id], [ten]) VALUES (3, N'Vest')
GO
INSERT [dbo].[LOAI] ([id], [ten]) VALUES (4, N'Phụ Kiện')
GO
SET IDENTITY_INSERT [dbo].[LOAI] OFF
GO
SET IDENTITY_INSERT [dbo].[LEPHUC] ON 
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (1, N'Váy cưới dáng suông', N'Váy cưới dáng suông là kiểu váy cưới ôm vừa vặn', N'Váy cưới dáng suông là kiểu váy cưới ôm vừa vặn', 6000000, N'vaycuoi1.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (2, N'Váy cưới dáng suông ', N'Váy cưới dáng suông là kiểu váy cưới ôm vừa vặn, bám sát đường viền cơ thể của bạn, là lựa chọn tốt cho tất cả cô dâu, đặc biệt cô dâu có vóc dáng nhỏ nhắn.', N'Váy cưới dáng suông là kiểu váy cưới ôm vừa vặn, bám sát đường viền cơ thể của bạn, là lựa chọn tốt cho tất cả cô dâu, đặc biệt cô dâu có vóc dáng nhỏ nhắn.', 6000000, N'vaycuoi2.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (3, N'Váy cưới dáng suông ', N'Những chiếc váy cưới suông dài chữ A còn được gọi với cái tên áo cưới nữ thần Hy Lạp (Empire Line Dresses). Thiết kế váy cưới form suông khá giống với trang phục của nữ thần Hy Lạp xưa, đều mang lại vẻ đẹp ngọt ngào, nhẹ nhàng và vô cùng cuốn hút.', N'Những chiếc váy cưới suông dài chữ A còn được gọi với cái tên áo cưới nữ thần Hy Lạp (Empire Line Dresses). Thiết kế váy cưới form suông khá giống với trang phục của nữ thần Hy Lạp xưa, đều mang lại vẻ đẹp ngọt ngào, nhẹ nhàng và vô cùng cuốn hút.', 6000000, N'vaycuoi3.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (4, N'Váy cưới xoè công chúa', N'Chiếc váy cưới xoè công chúa khiến cho bạn trở nên lộng lẫy, sang trọng hơn bao giờ hết. Dù đã xuất hiện từ lâu nhưng kiểu dáng này luôn là lựa chọn yêu thích nhất. Sở hữu phom dáng cổ điển, chưa bao giờ lỗi thời. Váy cưới xòe là kiểu thiết kế với thân trên ôm sát cơ thể, từ phần eo trở xuống xòe ra.', N'Chiếc váy cưới xoè công chúa khiến cho bạn trở nên lộng lẫy, sang trọng hơn bao giờ hết. Dù đã xuất hiện từ lâu nhưng kiểu dáng này luôn là lựa chọn yêu thích nhất. Sở hữu phom dáng cổ điển, chưa bao giờ lỗi thời. Váy cưới xòe là kiểu thiết kế với thân trên ôm sát cơ thể, từ phần eo trở xuống xòe ra.', 10000000, N'vaycuoi4.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (5, N'Váy cưới xoè công chúa', N'Chiếc váy cưới xoè công chúa khiến cho bạn trở nên lộng lẫy, sang trọng hơn bao giờ hết. Dù đã xuất hiện từ lâu nhưng kiểu dáng này luôn là lựa chọn yêu thích nhất. Sở hữu phom dáng cổ điển, chưa bao giờ lỗi thời. Váy cưới xòe là kiểu thiết kế với thân trên ôm sát cơ thể, từ phần eo trở xuống xòe ra.', N'Chiếc váy cưới xoè công chúa khiến cho bạn trở nên lộng lẫy, sang trọng hơn bao giờ hết. Dù đã xuất hiện từ lâu nhưng kiểu dáng này luôn là lựa chọn yêu thích nhất. Sở hữu phom dáng cổ điển, chưa bao giờ lỗi thời. Váy cưới xòe là kiểu thiết kế với thân trên ôm sát cơ thể, từ phần eo trở xuống xòe ra.', 10000000, N'vaycuoi5.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (6, N'Váy cưới xoè công chúa', N'Chiếc váy cưới xoè công chúa khiến cho bạn trở nên lộng lẫy, sang trọng hơn bao giờ hết. Dù đã xuất hiện từ lâu nhưng kiểu dáng này luôn là lựa chọn yêu thích nhất. Sở hữu phom dáng cổ điển, chưa bao giờ lỗi thời. Váy cưới xòe là kiểu thiết kế với thân trên ôm sát cơ thể, từ phần eo trở xuống xòe ra.', N'Chiếc váy cưới xoè công chúa khiến cho bạn trở nên lộng lẫy, sang trọng hơn bao giờ hết. Dù đã xuất hiện từ lâu nhưng kiểu dáng này luôn là lựa chọn yêu thích nhất. Sở hữu phom dáng cổ điển, chưa bao giờ lỗi thời. Váy cưới xòe là kiểu thiết kế với thân trên ôm sát cơ thể, từ phần eo trở xuống xòe ra.', 10000000, N'vaycuoi6.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (7, N'Váy cưới đuôi cá', N'Váy cưới đuôi cá có thiết kế đặc trưng ôm sát cơ thể, giúp bạn khéo léo khoe trọn những đường cong quyến rũ, thu hút mắt người đối diện.', N'Váy cưới đuôi cá có thiết kế đặc trưng ôm sát cơ thể, giúp bạn khéo léo khoe trọn những đường cong quyến rũ, thu hút mắt người đối diện.', 8000000, N'vaycuoi7.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (8, N'Váy cưới đuôi cá', N'Váy cưới đuôi cá có thiết kế đặc trưng ôm sát cơ thể, giúp bạn khéo léo khoe trọn những đường cong quyến rũ, thu hút mắt người đối diện.', N'Váy cưới đuôi cá có thiết kế đặc trưng ôm sát cơ thể, giúp bạn khéo léo khoe trọn những đường cong quyến rũ, thu hút mắt người đối diện.', 8000000, N'vaycuoi8.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (9, N'Váy cưới đuôi cá', N'Váy cưới đuôi cá có thiết kế đặc trưng ôm sát cơ thể, giúp bạn khéo léo khoe trọn những đường cong quyến rũ, thu hút mắt người đối diện.', N'Váy cưới đuôi cá có thiết kế đặc trưng ôm sát cơ thể, giúp bạn khéo léo khoe trọn những đường cong quyến rũ, thu hút mắt người đối diện.', 6800000, N'vaycuoi9.webp', 1)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (13, N'Áo dài đỏ trơn', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', 180000, N'aodai1.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (14, N'Áo dài kem trơn', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', 180000, N'aodai2.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (15, N'Áo dài vàng hoa', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', 180000, N'aodai3.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (16, N'Áo dài xanh trơn', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', 180000, N'aodai4.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (17, N'Áo dài xanh hoa', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', 180000, N'aodai5.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (18, N'Áo dài trắng hoa', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', N'Đã từ lâu, mẫu áo dài bưng quả màu xanh ngọc được ví như một cơn gió mới lạ mát lành, nhẹ nhàng khiến người nhìn cảm thấy dễ chịu và thoải mái nhất khi mặc. Không những thế, màu xanh ngọc còn tượng trưng cho cuộc sống hôn nhân hạnh phúc và yên bình của những cặp vợ chồng luôn mặn nồng bên nhau.', 180000, N'aodai6.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (19, N'Áo dài cô dâu đỏ', N'Áo dài cưới giúp cho diện mạo của các nàng dâu được long lanh và ấn tượng hơn so với các mẫu áo cưới thông thường. Hãy cùng chiêm ngưỡng qua các mẫu áo dài cưới đẹp nhất', N'Áo dài cưới giúp cho diện mạo của các nàng dâu được long lanh và ấn tượng hơn so với các mẫu áo cưới thông thường. Hãy cùng chiêm ngưỡng qua các mẫu áo dài cưới đẹp nhất', 2000000, N'aodai7.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (20, N'Áo dài cô dâu hồng', N'Áo dài cưới giúp cho diện mạo của các nàng dâu được long lanh và ấn tượng hơn so với các mẫu áo cưới thông thường. Hãy cùng chiêm ngưỡng qua các mẫu áo dài cưới đẹp nhất', N'Áo dài cưới giúp cho diện mạo của các nàng dâu được long lanh và ấn tượng hơn so với các mẫu áo cưới thông thường. Hãy cùng chiêm ngưỡng qua các mẫu áo dài cưới đẹp nhất', 2000000, N'aodai8.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (21, N'Áo dài cô dâu trắng', N'Áo dài cưới giúp cho diện mạo của các nàng dâu được long lanh và ấn tượng hơn so với các mẫu áo cưới thông thường. Hãy cùng chiêm ngưỡng qua các mẫu áo dài cưới đẹp nhất', N'Áo dài cưới giúp cho diện mạo của các nàng dâu được long lanh và ấn tượng hơn so với các mẫu áo cưới thông thường. Hãy cùng chiêm ngưỡng qua các mẫu áo dài cưới đẹp nhất', 2000000, N'aodai9.webp', 2)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (22, N'Vest caro xanh nhạt', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1000000, N'vest1.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (23, N'Vest đen', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1000000, N'vest2.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (24, N'Vest caro xám', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1000000, N'vest3.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (25, N'Vest caro xanh đậm', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1000000, N'vest4.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (26, N'Vest caro đen vàng', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1000000, N'vest5.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (27, N'Vest hồng', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1000000, N'vest6.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (28, N'Vest sọc xanh', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1700000, N'vest7.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (29, N'Vest sọc xám', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1700000, N'vest8.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (30, N'Vest trắng', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', N'Yếu tố quan trọng trong cách chọn vest cưới đó là sự tinh tế, thời thượng và độc đáo. Một bộ vest cưới không chỉ là trang phục mặc vào ngày cưới mà nó còn thể hiện cái gout thẩm mỹ của chú rể.', 1700000, N'vest9.webp', 3)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (32, N'Vươn miện cô dâu', N'Người ta vẫn luôn nói rằng cô dâu luôn là người đẹp nhất trong ngày cưới. Và bạn sẽ trở thành cô dâu đẹp hơn nữa nếu sử dụng những mẫu vương miện cô dâu tuyệt đẹp', N'Người ta vẫn luôn nói rằng cô dâu luôn là người đẹp nhất trong ngày cưới. Và bạn sẽ trở thành cô dâu đẹp hơn nữa nếu sử dụng những mẫu vương miện cô dâu tuyệt đẹp', 120000, N'vuongmien1.jpg', 4)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (33, N'Vương miện cô dâu dáng thái ', N'Người ta vẫn luôn nói rằng cô dâu luôn là người đẹp nhất trong ngày cưới. Và bạn sẽ trở thành cô dâu đẹp hơn nữa nếu sử dụng những mẫu vương miện cô dâu tuyệt đẹp', N'Người ta vẫn luôn nói rằng cô dâu luôn là người đẹp nhất trong ngày cưới. Và bạn sẽ trở thành cô dâu đẹp hơn nữa nếu sử dụng những mẫu vương miện cô dâu tuyệt đẹp', 280000, N'vuongmien2.jpg', 4)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (34, N'Vươn miện hoa hậu', N'Người ta vẫn luôn nói rằng cô dâu luôn là người đẹp nhất trong ngày cưới. Và bạn sẽ trở thành cô dâu đẹp hơn nữa nếu sử dụng những mẫu vương miện cô dâu tuyệt đẹp', N'Người ta vẫn luôn nói rằng cô dâu luôn là người đẹp nhất trong ngày cưới. Và bạn sẽ trở thành cô dâu đẹp hơn nữa nếu sử dụng những mẫu vương miện cô dâu tuyệt đẹp', 1500000, N'vuongmien3.jpg', 4)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (37, N'Cà vạt đỏ kẻ xanh', N'Mẫu cà vạt của được làm từ lụa tổng hợp có thiết kế mang đến vẻ ngoài lịch sự cho phái mạnh. Sản phẩm đã được kiểm chọn đảm bảo độ thoải mái, phù hợp với dáng người và dễ phối cùng áo sơ mi, vest.', N'Mẫu cà vạt của được làm từ lụa tổng hợp có thiết kế mang đến vẻ ngoài lịch sự cho phái mạnh. Sản phẩm đã được kiểm chọn đảm bảo độ thoải mái, phù hợp với dáng người và dễ phối cùng áo sơ mi, vest.', 120000, N'cavat1.jpg', 4)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (38, N'Cà vạt đen chấm bi', N'Mẫu cà vạt của được làm từ lụa tổng hợp có thiết kế mang đến vẻ ngoài lịch sự cho phái mạnh. Sản phẩm đã được kiểm chọn đảm bảo độ thoải mái, phù hợp với dáng người và dễ phối cùng áo sơ mi, vest.', N'Mẫu cà vạt của được làm từ lụa tổng hợp có thiết kế mang đến vẻ ngoài lịch sự cho phái mạnh. Sản phẩm đã được kiểm chọn đảm bảo độ thoải mái, phù hợp với dáng người và dễ phối cùng áo sơ mi, vest.', 120000, N'cavat2.jpg', 4)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (39, N'Cà vạt xanh kẻ trắng', N'Mẫu cà vạt của được làm từ lụa tổng hợp có thiết kế mang đến vẻ ngoài lịch sự cho phái mạnh. Sản phẩm đã được kiểm chọn đảm bảo độ thoải mái, phù hợp với dáng người và dễ phối cùng áo sơ mi, vest.', N'Mẫu cà vạt của được làm từ lụa tổng hợp có thiết kế mang đến vẻ ngoài lịch sự cho phái mạnh. Sản phẩm đã được kiểm chọn đảm bảo độ thoải mái, phù hợp với dáng người và dễ phối cùng áo sơ mi, vest.', 120000, N'cavat3.jpg', 4)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (40, N'Hoa cưới tulip', N'Bó hoa cưới sang trọng và tinh tế đến từ màu trắng của hoa tulip. Bó hoa thích hợp cho ngày trọng đại của bạn.', N'Bó hoa cưới sang trọng và tinh tế đến từ màu trắng của hoa tulip. Bó hoa thích hợp cho ngày trọng đại của bạn.', 1350000, N'hoacuoi1.webp', 4)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (41, N'Hoa cưới hoa hồng', N'Bó hoa cưới sang trọng và tinh tế đến từ màu trắng của hoa tulip. Bó hoa thích hợp cho ngày trọng đại của bạn.', N'Bó hoa cưới sang trọng và tinh tế đến từ màu trắng của hoa tulip. Bó hoa thích hợp cho ngày trọng đại của bạn.', 750000, N'hoacuoi2.webp', 4)
GO
INSERT [dbo].[LEPHUC] ([id], [ten], [mota], [noidung], [gia], [hinh], [idloai]) VALUES (42, N'Hoa cưới hoa hồng Đà Lạt', N'Bó hoa cầm tay cô dâu hạnh phúc được làm từ các loại hoa hồng nhập kết hợp với hoa hồng Đà Lạt và các loại lá phụ trang trí. Hãy là cô dâu đẹp nhất với bó hoa cưới cầm tay', N'Bó hoa cầm tay cô dâu hạnh phúc được làm từ các loại hoa hồng nhập kết hợp với hoa hồng Đà Lạt và các loại lá phụ trang trí. Hãy là cô dâu đẹp nhất với bó hoa cưới cầm tay', 1850000, N'hoacuoi3.webp', 4)
GO
SET IDENTITY_INSERT [dbo].[LEPHUC] OFF
GO
SET IDENTITY_INSERT [dbo].[DICHVU] ON 
GO
INSERT [dbo].[DICHVU] ([id], [ten], [gia], [noidung], [mota], [hinhanh1], [hinhanh2], [href]) VALUES (1, N'Makeup', 1800000, N'Make up tại cửa hàng: 800.000 vnđ/ lần Make up nội thành 10 km: 1.000.000 vnđ/ lần
					Make up từ 20 – 30 km: 1.400.00 vnđ/ lần
					Make up từ 31 – 40 km: 1.600.000 vnđ/ lần
					Make up từ 40 – 60 km: 1.800.000 vnđ/ lần', N'Nếu bạn đang tìm kiếm một địa chỉ uy tín và chuyên nghiệp để làm đẹp cho mình, makeup tại Chou Bridal-Makeup chính là sự lựa chọn hoàn hảo cho bạn. Với đội ngũ chuyên viên trang điểm giàu kinh nghiệm cùng những sản phẩm chất lượng cao, Chou Bridal-Makeup cam kết mang đến cho khách hàng những trải nghiệm làm đẹp tốt nhất. ', N'dv11.png', N'dv12.png', N'g4koU9MFXgM')
GO
INSERT [dbo].[DICHVU] ([id], [ten], [gia], [noidung], [mota], [hinhanh1], [hinhanh2], [href]) VALUES (2, N'Chụp Ảnh Cưới', 4290000, N'Bạn đang chuẩn bị cho ngày cưới của mình và có nhu cầu chụp ảnh cưới tuyệt đẹp? Hãy đến với TAMA Studio, một địa chỉ uy tín và chuyên nghiệp trong việc chụp ảnh cưới tại TPHCM. Với nhiều năm kinh nghiệm và sự tâm huyết của các chuyên gia trong lĩnh vực này, TAMA Studio mang đến cho bạn những khoảnh khắc đáng nhớ nhất trong cuộc đời.

', N'Bạn đang chuẩn bị cho ngày cưới của mình và có nhu cầu chụp ảnh cưới tuyệt đẹp? Hãy đến với TAMA Studio, một địa chỉ uy tín và chuyên nghiệp trong việc chụp ảnh cưới tại TPHCM. Với nhiều năm kinh nghiệm và sự tâm huyết của các chuyên gia trong lĩnh vực này, TAMA Studio mang đến cho bạn những khoảnh khắc đáng nhớ nhất trong cuộc đời.

', N'dv21.png', N'dv21.png', N'g4koU9MFXgM')
GO
INSERT [dbo].[DICHVU] ([id], [ten], [gia], [noidung], [mota], [hinhanh1], [hinhanh2], [href]) VALUES (3, N'Phóng Sự Cưới', 5290000, N'Quay phóng sự cưới là kiểu làm phim cưới theo phong cách phóng sự và có nguồn gốc từ Tây Âu. Quay phóng sự cưới kể lại chuyện ngày cưới của cặp đôi một cách sáng tạo và đầy màu sắc. Chú trọng đến cảm xúc của cô dâu, chú rể và những người xung quanh.

Trước khi quay phóng sự cưới, cô dâu chú rể và ekip sẽ bàn bạc và lên kịch bản chi tiết. Nhiếp ảnh gia sẽ theo đó để quay lại câu chuyện tình yêu của hai bạn. Từ cảnh chuẩn bị tiệc cưới, tâm trạng của cô dâu cho rể. Và còn cho đến khi cả hai trao nhau nhẫn cưới và nhận lời chúc phúc từ quan khách. Tùy vào kịch bản và kinh nghiệm của nhiếp ảnh gia mà mỗi câu chuyện tình lại được thể hiện theo một cách độc đáo riêng.

Quay phóng sự cưới yêu cầu nhiếp ảnh gia phải có kinh nghiệm và tay nghề cao. Vì thế, để có thể nhanh nhạy ghi lại những nụ cười, giọt nước mắt hạnh phúc tự nhiên chân thực nhất.', N'Từ ánh mắt đầm ấm của người cha trao cho con gái yêu khi về nhà chồng, đến những giọt nước mắt của cô dâu. Đám cưới đời người chỉ mong một lần, khoảnh khắc trong ngày vui ấy cũng nên được một lần đầu tư. Để có thể cho vào thước phim chỉnh chu, cùng với đoạn nhạc phù hợp.', N'dv31.png', N'dv21.png', N'g4koU9MFXgM')
GO
SET IDENTITY_INSERT [dbo].[DICHVU] OFF
GO
SET IDENTITY_INSERT [dbo].[BAIVIET] ON 
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (1, N'Hạnh Phúc Ngọt Ngào: Tiệc Cưới Đáng Nhớ của Chúng Tôi!', N'Chúng tôi vô cùng hạnh phúc và tự hào thông báo về sự kiện đặc biệt - ngày chúng tôi trở thành một gia đình! Cùng chúng tôi chia sẻ niềm vui trong ngày cưới của chúng tôi, một buổi tiệc đầy ắp tình yêu, tiếng cười và kỷ niệm đáng nhớ.', CAST(N'2023-11-30' AS Date), N'bv5.png')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (2, N'Hành Trình Tìm Đến Hạnh Phúc: Tiệc Cưới Của Chúng Tôi', N'Chúng tôi đã đi qua nhiều thử thách và gặt hái được hạnh phúc trong tình yêu của mình. Đây không chỉ là một buổi tiệc cưới, mà còn là hành trình của chúng tôi, được chia sẻ với bạn bè và gia đình. Cùng chúng tôi kỷ niệm ngày này, ngày mà tình yêu của chúng tôi được củng cố và chúng tôi trở thành một.', CAST(N'2023-11-30' AS Date), N'bv6.jpg')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (3, N'Tiệc Cưới Với Chút Hương Hoa và Tình Yêu Thắm Thiết', N'Mỗi chi tiết của buổi tiệc cưới của chúng tôi được chọn kỹ lưỡng, từ hương hoa nhẹ nhàng đến âm nhạc đầy cảm xúc. Chúng tôi chờ đợi để chia sẻ niềm hạnh phúc và sự ấm áp này với bạn bè và người thân. Hãy đến và chia vui cùng chúng tôi trong ngày quan trọng này!', CAST(N'2023-11-30' AS Date), N'bv7.jpg')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (4, N'Hòa Nhạc Tình Yêu: Tiệc Cưới Lãng Mạn của Chúng Tôi', N'Đám cưới của chúng tôi sẽ không chỉ là một buổi tiệc, mà còn là một buổi hòa nhạc của tình yêu. Được chúng tôi tổ chức với tâm huyết và niềm đam mê, buổi tiệc cưới này sẽ đánh thức mọi giác quan của bạn, để bạn có thể cảm nhận được tình yêu chân thành và sự đoàn kết của gia đình.', CAST(N'2023-11-30' AS Date), N'bv8.png')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (5, N'Bắt Đầu Hạnh Phúc: Tiệc Cưới Mơ Ước của Chúng Tôi', N'Đối với chúng tôi, ngày cưới không chỉ là sự kết hợp của hai con tim, mà còn là bắt đầu của một cuộc hành trình mới đầy hạnh phúc và ý nghĩa. Hãy đến và chia sẻ niềm vui này với chúng tôi, để chúng ta cùng chứng kiến sức mạnh của tình yêu và sự kỳ diệu của hôn nhân.', CAST(N'2023-11-30' AS Date), N'bv9.jpg')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (6, N'Nụ Cười, Tiếng Cười và Tình Yêu: Tiệc Cưới Của Chúng Tôi', N'Buổi tiệc cưới của chúng tôi sẽ đầy ắp với nụ cười và tiếng cười, là biểu hiện của niềm hạnh phúc và sự ấm áp. Chúng tôi mời bạn đến tham gia cùng chúng tôi trong ngày này, để chứng kiến những khoảnh khắc đáng nhớ và chia sẻ niềm vui không ngừng của tình yêu đẹp.', CAST(N'2023-11-30' AS Date), N'bv10.jpg')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (7, N'Tiệc Cưới Từ Trái Tim: Khi Tình Yêu Trở Thành Hôn Nhân', N'Hôm nay, tình yêu của chúng tôi sẽ được gắn kết trong hôn nhân, và chúng tôi muốn chia sẻ niềm hạnh phúc này với bạn. Buổi tiệc cưới của chúng tôi không chỉ là một buổi lễ, mà là một cam kết trọn đời và một dấu mốc quan trọng trong cuộc sống của chúng tôi. Hãy đến và chúc phúc cho chúng tôi trong ngày này!', CAST(N'2023-11-30' AS Date), N'bv11.jpg')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (8, N'Tiệc Cưới Đẹp Nhất: Tiệc Cưới Ngọt Ngào của Chúng Tôi', N'Trong bức tranh đẹp nhất của cuộc đời, ngày cưới của chúng tôi là điểm nhấn sáng nhất. Hãy đến và chia sẻ niềm vui của chúng tôi trong buổi tiệc cưới này, nơi mà tình yêu và sự đồng lòng tin được kỷ niệm một cách trang trọng và ấm áp.', CAST(N'2023-11-30' AS Date), N'bv12.jpg')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (9, N'Chúng Tôi Đang Đợi Chờ Ngày Hạnh Phúc: Tiệc Cưới Của Chúng Tôi', N'Niềm vui chưa từng giảm đi khi chúng tôi nghĩ về ngày hạnh phúc này. Sự chờ đợi và hồi hộp đã biến thành hiện thực, và chúng tôi muốn mời bạn đến chia sẻ niềm hạnh phúc này với chúng tôi trong buổi tiệc cưới ấm áp và ý nghĩa của chúng tôi.', CAST(N'2023-11-30' AS Date), N'bv13.png')
GO
INSERT [dbo].[BAIVIET] ([id], [tieude], [noidung], [ngaydang], [hinhanh]) VALUES (10, N'Tiệc Cưới Như Mơ: Khi Hai Trái Tim Hòa Quyện Thành Một', N'Chúng tôi không thể tin được rằng ngày này đã đến! Buổi tiệc cưới của chúng tôi không chỉ là một cơ hội để chúng tôi chia sẻ niềm hạnh phúc với bạn bè và gia đình, mà còn là một khoảnh khắc quan trọng đánh dấu sự hòa quyện của hai trái tim trong tình yêu và lòng tin. Hãy đến và chúc phúc cho chúng tôi trong ngày này, khi chúng tôi bắt đầu cuộc hành trình mới đầy yêu thương và đồng lòng tin.', CAST(N'2023-11-30' AS Date), N'bv14.jpg')
GO
SET IDENTITY_INSERT [dbo].[BAIVIET] OFF

GO
SET IDENTITY_INSERT [dbo].[DONHANG] ON 

INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (1, N'admin', CAST(N'2023-12-11T15:10:46.330' AS DateTime), 18000000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (2, N'admin', CAST(N'2023-12-11T15:16:17.677' AS DateTime), 5000000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (3, N'admin', CAST(N'2023-12-11T15:17:58.853' AS DateTime), 8400000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (4, N'admin', CAST(N'2023-12-11T15:19:10.067' AS DateTime), 600000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (5, N'user3', CAST(N'2023-12-11T15:20:49.243' AS DateTime), 180000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (6, N'user3', CAST(N'2023-12-11T15:21:11.653' AS DateTime), 17000000, N'', 5, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (7, N'admin', CAST(N'2023-12-12T10:54:35.530' AS DateTime), 6000000, N'', 3, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (9, N'admin', CAST(N'2023-12-12T10:56:40.063' AS DateTime), 6000000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (10, N'admin', CAST(N'2023-12-12T10:57:23.503' AS DateTime), 8000000, N'', 3, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (11, N'admin', CAST(N'2023-12-12T11:01:07.367' AS DateTime), 6000000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (12, N'admin', CAST(N'2023-12-12T11:01:12.813' AS DateTime), 6050000, N'quận 12', 3, 0, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (13, N'admin', CAST(N'2023-12-12T11:02:18.750' AS DateTime), 10000000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (14, N'admin', CAST(N'2023-12-12T11:02:46.977' AS DateTime), 6800000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (15, N'admin', CAST(N'2023-12-12T11:04:31.737' AS DateTime), 0, N'', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (16, N'admin', CAST(N'2023-12-12T11:08:47.193' AS DateTime), 8000000, N'', 5, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (17, N'admin', CAST(N'2023-12-12T11:08:58.350' AS DateTime), 8050000, N'Quận 12', 3, 0, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (18, N'admin', CAST(N'2023-12-12T11:09:12.023' AS DateTime), 8000000, N'', 3, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (19, N'admin', CAST(N'2023-12-12T11:09:37.550' AS DateTime), 230000, N'Quận 12', 4, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (20, N'admin', CAST(N'2023-12-12T11:10:30.137' AS DateTime), 1080000, N'', 3, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (21, N'admin', CAST(N'2023-12-12T11:10:40.183' AS DateTime), 1080000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (22, N'admin', CAST(N'2023-12-12T11:11:03.477' AS DateTime), 540000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (23, N'admin', CAST(N'2023-12-12T11:12:28.773' AS DateTime), 8050000, N'Quận 12', 3, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (28, N'admin', CAST(N'2023-12-13T03:43:59.680' AS DateTime), 66000000, N'', 3, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (29, N'admin', CAST(N'2023-12-13T03:52:58.393' AS DateTime), 6000000, N'', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (30, N'admin', CAST(N'2023-12-13T03:54:58.487' AS DateTime), 10000000, N'', 3, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (31, N'admin', CAST(N'2023-12-13T03:58:28.330' AS DateTime), 6000000, N'', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (35, N'admin', CAST(N'2023-12-13T04:00:06.847' AS DateTime), 6000000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (36, N'admin', CAST(N'2023-12-13T04:00:44.200' AS DateTime), 8000000, N'', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (37, N'admin', CAST(N'2023-12-13T04:01:05.523' AS DateTime), 8000000, N'', 3, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (38, N'admin', CAST(N'2023-12-13T04:04:46.060' AS DateTime), 280000, N'', 4, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (39, N'admin', CAST(N'2023-12-13T04:05:12.700' AS DateTime), 1700000, N'', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (40, N'admin', CAST(N'2023-12-13T04:05:54.287' AS DateTime), 1700000, N'', 3, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (41, N'admin', CAST(N'2023-12-13T04:06:53.957' AS DateTime), 750000, N'', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (42, N'admin', CAST(N'2023-12-13T04:29:46.910' AS DateTime), 750000, N'', 5, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (43, N'admin', CAST(N'2023-12-13T04:30:50.267' AS DateTime), 8000000, N'Quận 12', 5, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (44, N'admin', CAST(N'2023-12-13T04:31:27.297' AS DateTime), 8050000, N'Quận 12', 3, 0, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (45, N'admin', CAST(N'2023-12-13T04:32:21.283' AS DateTime), 800000, N'Quận 11', 5, 0, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (46, N'admin', CAST(N'2023-12-13T04:33:06.107' AS DateTime), 2000000, N'Quận 12', 3, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (47, N'admin', CAST(N'2023-12-13T04:37:55.907' AS DateTime), 1000000, N'Quận 12', 3, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (48, N'admin', CAST(N'2023-12-13T04:39:58.393' AS DateTime), 6800000, N'Quận 12', 4, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (49, N'admin', CAST(N'2023-12-13T04:43:01.587' AS DateTime), 230000, N'Quận 12', 4, 0, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (50, N'admin', CAST(N'2023-12-13T04:46:24.677' AS DateTime), 180000, N'Quận 12', 5, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (52, N'admin', CAST(N'2023-12-13T06:51:25.537' AS DateTime), 230000, N'Quận 12', 4, 0, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (53, N'admin', CAST(N'2023-12-13T06:54:54.113' AS DateTime), 180000, N'Quận 12', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (54, N'admin', CAST(N'2023-12-13T06:55:35.323' AS DateTime), 8000000, N'Quận 12', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (55, N'admin', CAST(N'2023-12-13T06:57:38.810' AS DateTime), 120000, N'Quận 12', 2, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (56, N'admin', CAST(N'2023-12-13T17:23:11.393' AS DateTime), 280000, N'Quận 12', 2, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (57, N'admin', CAST(N'2023-12-13T17:25:59.490' AS DateTime), 120000, N'Quận 12', 2, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (58, N'admin', CAST(N'2023-12-13T17:32:04.527' AS DateTime), 120000, N'Quận 12', 2, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (59, N'admin', CAST(N'2023-12-13T17:33:02.487' AS DateTime), 230000, N'Quận 12', 5, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (60, N'admin', CAST(N'2023-12-13T17:34:16.010' AS DateTime), 2050000, N'Quận 12', 4, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (61, N'admin', CAST(N'2023-12-13T17:36:29.810' AS DateTime), 170000, N'Tô ký, phường Tân Chánh Hiệp, Quận 12, Thành phố Hồ Chí Minh', 2, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (63, N'admin', CAST(N'2023-12-13T17:53:50.420' AS DateTime), 1000000, N'Quận 12', 4, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (64, N'admin', CAST(N'2023-12-13T17:54:50.157' AS DateTime), 1050000, N'Quận 12, TPHCM', 4, 0, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (65, N'admin', CAST(N'2023-12-13T18:02:55.413' AS DateTime), 8000000, N'Quận 12', 2, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (66, N'admin', CAST(N'2023-12-13T18:24:29.533' AS DateTime), 0, N'Quận 12', 2, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (69, N'user1', CAST(N'2023-12-14T00:30:41.547' AS DateTime), 20000000, N'Quận 12', 2, 1, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (70, N'user2', CAST(N'2023-12-14T00:31:06.003' AS DateTime), 1230000, N'596 Tô Ký, Tân Chánh Hiệp, Quận 12, Thành phố Hồ Chí Minh', 1, 0, 1)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (71, N'user3', CAST(N'2023-12-14T00:33:38.930' AS DateTime), 1180000, N'Quận 12', 2, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (78, N'user1', CAST(N'2023-12-14T04:25:59.517' AS DateTime), 1700000, NULL, 2, 1, 0)
INSERT [dbo].[DONHANG] ([id], [tentaikhoan], [ngaytao], [tongtien], [diachi], [trangthai], [cachnhan], [hinhthuc]) VALUES (81, N'user1', CAST(N'2023-12-14T06:27:32.410' AS DateTime), 1120000, N'', 2, 1, 1)
SET IDENTITY_INSERT [dbo].[DONHANG] OFF
GO

GO
SET IDENTITY_INSERT [dbo].[CHITIETDONHANG] ON 

INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (1, 1, 1, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (2, 1, 3, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (3, 1, 2, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (4, 2, 26, 5, 1000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (5, 3, 1, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (6, 3, 13, 10, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (7, 3, 32, 5, 120000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (8, 4, 37, 5, 120000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (9, 5, 17, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (10, 6, 29, 10, 1700000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (11, 7, 3, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (12, 9, 2, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (13, 10, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (14, 11, 2, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (15, 12, 1, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (16, 13, 5, 1, 10000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (17, 14, 9, 1, 6800000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (18, 16, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (19, 17, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (20, 18, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (21, 19, 17, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (22, 20, 14, 6, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (23, 21, 14, 6, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (24, 22, 13, 3, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (25, 23, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (30, 28, 1, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (31, 28, 4, 6, 10000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (32, 29, 2, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (33, 30, 4, 1, 10000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (34, 31, 1, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (35, 35, 1, 1, 6000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (36, 36, 7, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (37, 37, 7, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (38, 38, 33, 1, 280000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (39, 39, 29, 1, 1700000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (40, 40, 29, 1, 1700000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (41, 41, 41, 1, 750000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (42, 42, 41, 1, 750000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (43, 43, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (44, 44, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (45, 45, 41, 1, 750000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (46, 46, 20, 1, 2000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (47, 47, 23, 1, 1000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (48, 48, 9, 1, 6800000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (49, 49, 14, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (50, 50, 18, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (51, 52, 18, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (52, 53, 14, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (53, 54, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (54, 55, 32, 1, 120000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (55, 56, 33, 1, 280000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (56, 57, 32, 1, 120000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (57, 58, 38, 1, 120000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (58, 59, 14, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (59, 60, 19, 1, 2000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (60, 61, 37, 1, 120000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (61, 63, 23, 1, 1000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (62, 64, 23, 1, 1000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (63, 65, 8, 1, 8000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (64, 69, 4, 1, 10000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (65, 69, 6, 1, 10000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (66, 70, 14, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (67, 70, 23, 1, 1000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (68, 71, 17, 1, 180000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (69, 71, 23, 1, 1000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (72, 78, 28, 1, 1700000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (73, 81, 22, 1, 1000000)
INSERT [dbo].[CHITIETDONHANG] ([id], [iddonhang], [idlephuc], [soluong], [gia]) VALUES (74, 81, 39, 1, 120000)
SET IDENTITY_INSERT [dbo].[CHITIETDONHANG] OFF
GO
SET IDENTITY_INSERT [dbo].[DATLICH] ON 

INSERT [dbo].[DATLICH] ([id], [tentaikhoan], [iddichvu], [ngaythue], [thoigian], [diachi], [ghichu], [cachnhan], [trangthai]) VALUES (21, N'admin', 2, CAST(N'2023-01-02' AS Date), CAST(N'2023-01-02T09:00:00.000' AS DateTime), N'DiaChi2', N'GhiChu2', 0, 2)
INSERT [dbo].[DATLICH] ([id], [tentaikhoan], [iddichvu], [ngaythue], [thoigian], [diachi], [ghichu], [cachnhan], [trangthai]) VALUES (22, N'user1', 1, CAST(N'2023-12-18' AS Date), CAST(N'1900-01-01T11:00:00.000' AS DateTime), N'', N'', 0, 3)
INSERT [dbo].[DATLICH] ([id], [tentaikhoan], [iddichvu], [ngaythue], [thoigian], [diachi], [ghichu], [cachnhan], [trangthai]) VALUES (23, N'user1', 1, CAST(N'2023-12-18' AS Date), CAST(N'1900-01-01T10:00:00.000' AS DateTime), N'', N'', 0, 1)
INSERT [dbo].[DATLICH] ([id], [tentaikhoan], [iddichvu], [ngaythue], [thoigian], [diachi], [ghichu], [cachnhan], [trangthai]) VALUES (24, N'user1', 1, CAST(N'2023-12-18' AS Date), CAST(N'1900-01-01T10:00:00.000' AS DateTime), N'', N'', 0, 1)
INSERT [dbo].[DATLICH] ([id], [tentaikhoan], [iddichvu], [ngaythue], [thoigian], [diachi], [ghichu], [cachnhan], [trangthai]) VALUES (25, N'user1', 1, CAST(N'2023-12-18' AS Date), CAST(N'1900-01-01T10:00:00.000' AS DateTime), N'', N'', 0, 1)
SET IDENTITY_INSERT [dbo].[DATLICH] OFF
GO
GO
SET IDENTITY_INSERT [dbo].[BINHLUANBAIVIET] ON 

INSERT [dbo].[BINHLUANBAIVIET] ([id], [noidung], [tentaikhoan], [idbaiviet]) VALUES (71, N'Chúng tôi vô cùng hạnh phúc và tự hào thông báo về sự kiện đặc biệt', N'user1', 1)
INSERT [dbo].[BINHLUANBAIVIET] ([id], [noidung], [tentaikhoan], [idbaiviet]) VALUES (72, N'Một buổi tiệc đầy ắp tình yêu, tiếng cười và kỷ niệm đáng nhớ.', N'user1', 1)
INSERT [dbo].[BINHLUANBAIVIET] ([id], [noidung], [tentaikhoan], [idbaiviet]) VALUES (73, N'Cùng chúng tôi chia sẻ niềm vui trong ngày cưới của chúng tôi', N'user2', 1)
INSERT [dbo].[BINHLUANBAIVIET] ([id], [noidung], [tentaikhoan], [idbaiviet]) VALUES (74, N'Một buổi tiệc đầy ắp tình yêu', N'user2', 1)
SET IDENTITY_INSERT [dbo].[BINHLUANBAIVIET] OFF
GO
SET IDENTITY_INSERT [dbo].[BINHLUANLEPHUC] ON 

INSERT [dbo].[BINHLUANLEPHUC] ([id], [noidung], [tentaikhoan], [idlephuc]) VALUES (67, N'Những tác phẩm của em thật tuyệt vời! Nàng phù thuỷ cho mọi ước mơ của các cô gái♥️♥️♥️♥️♥️', N'admin', 14)
INSERT [dbo].[BINHLUANLEPHUC] ([id], [noidung], [tentaikhoan], [idlephuc]) VALUES (68, N'Những tác phẩm của em thật tuyệt vời! ', N'admin', 14)
INSERT [dbo].[BINHLUANLEPHUC] ([id], [noidung], [tentaikhoan], [idlephuc]) VALUES (69, N'Sản phẩm đẹp, mình cũng muốn thuê ', N'admin', 1)
INSERT [dbo].[BINHLUANLEPHUC] ([id], [noidung], [tentaikhoan], [idlephuc]) VALUES (70, N'Sẽ tuyệt vời nếu được sở hữu lễ phục này vào ngày cưới', N'admin', 1)
INSERT [dbo].[BINHLUANLEPHUC] ([id], [noidung], [tentaikhoan], [idlephuc]) VALUES (71, N'Váy cưới dáng suông là kiểu váy cưới ôm vừa vặn', N'user2', 1)
INSERT [dbo].[BINHLUANLEPHUC] ([id], [noidung], [tentaikhoan], [idlephuc]) VALUES (72, N' Là lựa chọn tốt cho tất cả cô dâu', N'user2', 1)
INSERT [dbo].[BINHLUANLEPHUC] ([id], [noidung], [tentaikhoan], [idlephuc]) VALUES (73, N'Váy đẹp quá ', N'user1', 1)
SET IDENTITY_INSERT [dbo].[BINHLUANLEPHUC] OFF
GO