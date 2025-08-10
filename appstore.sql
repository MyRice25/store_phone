-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 29, 2022 lúc 05:17 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `appstore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `menu_navigation`
--

CREATE TABLE `menu_navigation` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `menu_navigation`
--

INSERT INTO `menu_navigation` (`id`, `name`, `image`) VALUES
(1, 'Home', 'https://img.icons8.com/bubbles/2x/home.png'),
(2, 'Cart', 'https://img.icons8.com/clouds/2x/shopping-cart-promotion.png'),
(3, 'Category', 'https://cdn-icons-png.flaticon.com/128/3843/3843517.png'),
(4, 'Account', 'https://img.icons8.com/clouds/2x/guest-male.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `username` text COLLATE utf8_unicode_ci NOT NULL,
  `email` text COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL,
  `amount` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `status` int(2) NOT NULL DEFAULT 0,
  `e-wallet` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order`
--

INSERT INTO `order` (`id`, `iduser`, `username`, `email`, `address`, `phone`, `message`, `amount`, `total`, `status`, `e-wallet`) VALUES
(1, 0, 'm', 'my@gmail.com', '456 btx', '123456', 'ksabdkas', 2, 8398, 0, ''),
(2, 0, 'M', 'my123@gmail.com', '132 btx', '132456', 'dsbfdsbfds', 2, 8398, 0, ''),
(3, 1, 'm', 'my@gmail.com', '789 br', '123456', 'fdgs', 1, 4199, 0, ''),
(4, 10, 'us', 'user2@gmail.com', '456 njkk', '456', 'order fast', 1, 30790, 0, ''),
(5, 9, 'user', 'user1@gmail.com', '789 nts', '123456789', 'order', 2, 13960, 3, ''),
(6, 9, 'user', 'user1@gmail.com', '99 nd', '123456789', 'mnn', 2, 13180, 2, ''),
(7, 9, 'user', 'user1@gmail.com', '4567 btv', '123456789', 'get', 1, 13990, 0, ''),
(8, 9, 'user', 'user1@gmail.com', '4567 btv', '123456789', 'get', 1, 13990, 0, ''),
(9, 9, 'user', 'user1@gmail.com', '4567 btv', '123456789', 'get', 1, 13990, 0, '1234456'),
(10, 9, 'user', 'user1@gmail.com', '4567 btv', '123456789', 'get', 1, 13990, 0, ''),
(11, 9, 'user', 'user1@gmail.com', '4567 btv', '123456789', 'get', 1, 13990, 0, ''),
(12, 9, 'user', 'user1@gmail.com', '78 yb', '123456789', 'tyg', 1, 19999, 0, ''),
(13, 9, 'user', 'user1@gmail.com', '56 yf', '123456789', 'huu', 1, 25600, 0, ''),
(14, 9, 'user', 'user1@gmail.com', '56 yf', '123456789', 'huu', 1, 25600, 0, ''),
(15, 9, 'user', 'user1@gmail.com', '56 yf', '123456789', 'huu', 1, 25600, 0, ''),
(16, 9, 'user', 'user1@gmail.com', '12 bv', '123456789', '45', 1, 20490, 0, ''),
(17, 9, 'user', 'user1@gmail.com', '65 b', '123456789', 'hcv', 1, 4690, 0, ''),
(18, 9, 'user', 'user1@gmail.com', '65 b', '123456789', 'hcv', 1, 4690, 0, ''),
(19, 9, 'user', 'user1@gmail.com', '456 hb', '123456789', 'gvcvb', 1, 3790, 0, ''),
(20, 9, 'user', 'user1@gmail.com', '456 hb', '123456789', 'gvcvb', 1, 3790, 0, ''),
(21, 9, 'user', 'user1@gmail.com', '456 hb', '123456789', 'gvcvb', 1, 3790, 0, ''),
(22, 9, 'user', 'user1@gmail.com', '455', '123456789', 'hh', 1, 10490, 0, ''),
(23, 9, 'user', 'user1@gmail.com', '455', '123456789', 'hh', 1, 10490, 0, ''),
(24, 9, 'user', 'user1@gmail.com', '46 bv', '123456789', 'ggc', 1, 9990, 0, ''),
(25, 9, 'user', 'user1@gmail.com', '56 bv', '123456789', 'gh', 1, 30790, 0, ''),
(26, 9, 'user', 'user1@gmail.com', '23 bn', '123456789', 'ghgg', 1, 2241, 0, 'v3/MOMOV8MG20220629MOMOV8MG20220629merchant_billId_16565224057237121390440596067'),
(27, 9, 'user', 'user1@gmail.com', '577 bn', '123456789', 'bnnj', 1, 2750, 0, ''),
(28, 9, 'user', 'user1@gmail.com', '899', '123456789', 'ghb', 1, 10490, 0, 'v3/MOMOV8MG20220629MOMOV8MG20220629merchant_billId_16565225358627121990802365724'),
(29, 9, 'user', 'user1@gmail.com', '895 nm', '123456789', 'ons', 3, 29970, 0, 'v3/MOMOV8MG20220629MOMOV8MG20220629merchant_billId_16565562671487155046194186435'),
(30, 9, 'user', 'user1@gmail.com', '896 nfj', '123456789', 'donate', 3, 16905, 0, '220630000002827n3i0283'),
(31, 9, 'user', 'user1@gmail.com', '456 dm', '123456789', 'dff', 2, 9380, 0, ''),
(32, 9, 'user', 'user1@gmail.com', '456', '123456789', 'hkm', 1, 10490, 0, ''),
(33, 9, 'user', 'user1@gmail.com', '789 nb', '123456789', '123 nn', 1, 38490, 0, ''),
(34, 9, 'user', 'user1@gmail.com', '789 sn', '123456789', 'mm', 1, 10490, 0, '220630000002862NOBLt7x'),
(35, 9, 'user', 'user1@gmail.com', '123 mn', '123456789', 'mn', 1, 10490, 0, 'v3/MOMOV8MG20220629MOMOV8MG20220629merchant_billId_16565624474927161213548772011'),
(36, 9, 'user', 'user1@gmail.com', 'mn12', '123456789', '12g', 1, 30790, 3, '220630000002894Q30Zz83'),
(37, 11, 'user 3', 'user3@gmail.com', '567 btnd', '764592433', 'fasst fasst', 4, 10491, 2, ''),
(38, 10, 'us', 'user2@gmail.com', '248', '456', 'buy', 4, 35260, 0, ''),
(39, 10, 'us', 'user2@gmail.com', '248', '456', 'buy', 4, 35260, 3, ''),
(40, 12, 'user 5', 'user5@gmail.com', '258', '123456', '123mh', 1, 1500, 0, ''),
(41, 12, 'user 5', 'user5@gmail.com', '258', '123456', '123mh', 1, 1500, 0, ''),
(42, 12, 'user 5', 'user5@gmail.com', '278', '123456', 'buy', 1, 30790, 0, 'v3/MOMOV8MG20220629MOMOV8MG20220629merchant_billId_1665029622933101533927335419'),
(43, 9, 'user', 'user4@gmail.com', '657', '123456789', 'bhnn', 1, 10490, 2, ''),
(44, 9, 'user', 'user4@gmail.com', '256', '123456789', 'bb', 1, 4199, 2, ''),
(45, 11, 'user 34', 'user33@gmail.com', '248 btx', '764592433', 'buy', 3, 45179, 0, ''),
(46, 11, 'user 34', 'user33@gmail.com', '123', '764592433', 'buy', 1, 20490, 0, 'v3/MOMOV8MG20220629MOMOV8MG20220629merchant_billId_16688476511531659402214988972');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id_order` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id_order`, `id_product`, `amount`, `price`) VALUES
(3, 1, 1, 4199),
(4, 50, 1, 30790),
(5, 40, 2, 6980),
(6, 45, 2, 6590),
(7, 39, 1, 13990),
(12, 21, 1, 19999),
(13, 49, 1, 25600),
(16, 2, 1, 20490),
(17, 3, 1, 4690),
(19, 4, 1, 3790),
(22, 51, 1, 10490),
(24, 48, 1, 9990),
(25, 50, 1, 30790),
(26, 35, 1, 2241),
(27, 32, 1, 2750),
(28, 51, 1, 10490),
(29, 48, 3, 9990),
(30, 38, 3, 5635),
(31, 46, 2, 4690),
(32, 51, 1, 10490),
(33, 47, 1, 38490),
(34, 51, 1, 10490),
(35, 51, 1, 10490),
(36, 50, 1, 30790),
(37, 32, 3, 2750),
(37, 35, 1, 2241),
(38, 51, 3, 10490),
(38, 4, 1, 3790),
(39, 4, 1, 3790),
(39, 51, 3, 10490),
(40, 55, 1, 1500),
(42, 50, 1, 30790),
(43, 51, 1, 10490),
(44, 1, 1, 4199),
(45, 1, 1, 4199),
(45, 2, 2, 20490),
(46, 2, 1, 20490);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_unicode_ci NOT NULL,
  `price` text COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `image`, `description`, `type`) VALUES
(1, 'Galaxy Z Fold3 5G', '4199', 'https://cdn.tgdd.vn/Products/Images/42/226935/samsung-galaxy-z-fold-3-silver-1-600x600.jpg', 'Samsung Galaxy Z Fold3 5G 256GB Phone Configuration\r\n•	Screen:\r\nDynamic AMOLED 2XMain 7.6\" & Sub 6.2\"Full HD+\r\n•	Operating system:\r\nAndroid 11\r\n• Rear camera:\r\n3 cameras 12 MP\r\n•	Front camera:\r\n10 MP & 4 MP\r\n• Chips:\r\nSnapdragon 888\r\n• RAM:\r\n12 GB\r\n•	Internal memory:\r\n256 GB\r\n• SIM:\r\n2 Nano SIMsSupport 5G\r\n•	Rechargeable batteries:\r\n4400 mAh25 W', 1),
(2, 'iPhone 12 64GB', '20490', 'https://cdn.tgdd.vn/Products/Images/42/213031/iphone-12-den-new-2-600x600.jpg', 'Phone Configuration iPhone 12 64GB\r\n•	Screen:\r\nOLED6.1\"Super Retina XDR\r\n•	Operating system:\r\niOS 14\r\n• Rear camera:\r\n2 cameras 12 MP\r\n•	Front camera:\r\n12 MP\r\n• Chips:\r\nApple A14 Bionic\r\n• RAM:\r\n4 GB\r\n•	Internal memory:\r\n64 GB\r\n• SIM:\r\n1 Nano SIM & 1 eSIMSupport 5G\r\n•	Rechargeable batteries:\r\n2815 mAh20 W', 1),
(3, 'Xiaomi Redmi 10 (6GB/128GB)', '4690', 'https://cdn.tgdd.vn/Products/Images/42/246200/redmi-10-gray-600x600.jpg', 'Configuration Phone Xiaomi Redmi 10 (6GB/128GB)\r\n•	Screen:\r\nIPS LCD6.5\"Full HD+\r\n•	Operating system:\r\nAndroid 11\r\n• Rear camera:\r\nMain 50 MP & Sub 8 MP, 2 MP, 2 MP\r\n•	Front camera:\r\n8 MP\r\n• Chips:\r\nMediaTek Helio G88 8 cores\r\n• RAM:\r\n6 GB\r\n•	Internal memory:\r\n128 GB\r\n• SIM:\r\n2 Nano SIM Support 4G\r\n•	Rechargeable batteries:\r\n5000 mAh18 W', 1),
(4, 'Vivo Y12s (4GB/128GB) ', '3790', 'https://cdn.tgdd.vn/Products/Images/42/230630/vivo-y12s-4gb-128gb-xanh-1-org.jpg', 'Vivo Y12s is equipped with an IPS LCD screen with a large 6.51-inch waterdrop design, combined with 2.5D curved glass for a great experience when watching movies, surfing the web, playing games, ... This display has an HD+ resolution that offers a wide viewing angle, more vivid visual experience when watching videos or playing games, with vivid color reproduction and high brightness making it easy to use your smartphone in everything. light event.\r\nIn addition, the Y12s screen is also equipped with a blue light filter to prevent eye strain when using the device for a long time.', 1),
(5, 'OPPO Reno6 Z 5G', '9490', 'https://cdn.tgdd.vn/Products/Images/42/239747/oppo-reno6-z-5g-bac-1-org.jpg', 'The rear camera system is equipped with state-of-the-art equipment, including a 64 MP main camera, an 8 MP ultra-wide-angle camera and a 2 MP macro camera and a 32 MP front camera that is always ready to capture all emotions in the frame, helping users feel comfortable. roof captures life\'s moments in the most impressive way. The main camera has a high resolution and thanks to the algorithm can increase the image resolution up to 108 MP. With this feature, it will be very easy for us to take pictures of crowds or distant scenes and when zooming in, the details are still very clear. Catching the trend with OPPO with the breakthrough feature \"Portrait Bokeh Flare\" is not merely removing fonts, but it will process the subject and background independently, thereby combining with AI technology to correct the background. The back light becomes a magical background, making the overall photo more shimmering and more artistic. SnapShot captures exposures up to 1/1000 ms outdoors in good light.', 1),
(6, 'OPPO A74 ', '6690', 'https://cdn.tgdd.vn/Products/Images/42/235653/oppo-a74-blue-9-600x600.jpg', 'Phone Configuration OPPO A74\r\n•	Screen:\r\nAMOLED6.43\"Full HD+\r\n•	Operating system:\r\nAndroid 11\r\n• Rear camera:\r\nMain 48 MP & Secondary 2 MP, 2 MP\r\n•	Front camera:\r\n16 MP\r\n• Chips:\r\nSnapdragon 662\r\n• RAM:\r\n8 GB\r\n•	Internal memory:\r\n128 GB\r\n• SIM:\r\n2 Nano SIM Support 4G\r\n•	Rechargeable batteries:\r\n5000 mAh33 W', 1),
(7, 'Realme C21Y 4GB ', '3890', 'https://cdn.tgdd.vn/Products/Images/42/241265/realme-c21y-den-1-org.jpg', 'Realme C21Y 4GB Phone Configuration\r\n•	Screen:\r\nIPS LCD6.5\"HD+\r\n•	Operating system:\r\nAndroid 11\r\n• Rear camera:\r\nMain 13 MP & Sub 2 MP, 2 MP\r\n•	Front camera:\r\n5 MP\r\n• Chips:\r\nSpreadtrum T610 8 cores\r\n• RAM:\r\n4 GB\r\n•	Internal memory:\r\n64 GB\r\n• SIM:\r\n2 Nano SIM Support 4G\r\n•	Rechargeable batteries:\r\n5000 mAh10 W', 1),
(8, 'iPhone 11 128GB ', '16990', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/i/p/iphone-11_2_.jpg', 'Specifications\r\nScreen size 6.1 inches\r\nIPS LCD screen technology\r\nRear camera Dual 12MP camera:\r\n- Wide-angle camera: /1.8 aperture\r\n- Ultra-wide camera: /2.4 aperture\r\nFront camera 12 MP, /2.2 aperture\r\nChipset A13 Bionic\r\nRAM capacity 4 GB', 1),
(9, 'Xiaomi Redmi Note 10 ', '5100', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/x/i/xiaomi-redmi-note-10_1.jpg', 'Specifications\r\nScreen size 6.43 inches\r\nAMOLED screen technology\r\nRear camera Wide-angle camera: 48 MP, f/1.8, 26mm, 1/2.0\", 0.8µm, PDAF\r\nSuper wide-angle camera: 8 MP, f/2.2, 118˚, 1/4.0\", 1.12µm\r\nMacro camera: 2 MP, f/2.4\r\nDepth sensor: 2 MP, f/2.4\r\nFront camera 13 MP, f/2.5, 1/3.06\", 1.12µm\r\nChipset Snapdragon 678 (11 nm)\r\nRAM capacity 6 GB', 1),
(10, 'Samsung Galaxy S20 FE 256GB ', '12350', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/s/a/samsung-galaxy-20-fe_4_.jpg', 'Specifications\r\nScreen size 6.5 inches\r\nSuper AMOLED screen technology\r\nRear camera Main camera: 12 MP, f/1.8\r\nTelephoto camera: 8 MP, f/2.0, 3x optical zoom\r\nSuper wide-angle camera: 12 MP, f/2.2\r\nFront camera 32 MP, f/2.0, AF\r\nChipset Snapdragon 865\r\nRAM capacity 8 GB', 1),
(11, 'Samsung Galaxy A52s 5G ', '10500', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/0/4/04_2_4.png', 'Specifications\r\nScreen size 6.5 inches\r\nSuper AMOLED screen technology\r\nRear camera Main camera: 64 MP (f/1.8) OIS\r\nSuper wide-angle camera: 12MP (f2.2)\r\nMacro camera close-up: 5MP (f/2.2)\r\nCamera to remove fonts: 5MP (f/2.4)\r\nFront Camera 32 MP (f2.2)\r\nOcta-core Snapdragon 778G 5G chipset\r\nRAM capacity 8 GB', 1),
(12, 'iPhone 13 Pro ', '29490', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone_13-_pro-5.jpg', 'Specifications\r\nScreen size 6.1 inches\r\nOLED screen technology\r\nRear camera Wide-angle camera: 12MP, /1.5\r\nSuper wide-angle camera: 12MP, /1.8\r\nTelephoto camera: 12MP, /2.8\r\nFront camera 12MP, /2.2\r\nChipset Apple A15\r\nRAM capacity 6 GB', 1),
(13, 'Xiaomi Redmi 9T 4GB 64GB ', '3400', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/x/i/xiaomi-redmi-9t_1_.jpg', 'Specifications\r\nScreen size 6.53 inches\r\nRear camera 48 MP, f/1.8, 26mm (wide), 1/2.0\", 0.8µm, PDAF\r\n8 MP, f/2.2, 120˚ (ultrawide), 1/4.0\", 1.12µm\r\n2 MP, f/2.4, (macro)\r\n2 MP, f/2.4, (depth)\r\nFront camera 8 MP, f/2.0, 27mm (wide), 1/4.0\", 1.12µm\r\nChipset Qualcomm SM6115 Snapdragon 662 (11 nm)\r\nRAM capacity 6 GB\r\n128 GB internal memory', 1),
(14, 'iPhone 12 Pro ', '26500', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/i/p/iphone-12-pro-max_1_.jpg', 'Specifications\r\nScreen size 6.1 inches\r\nOLED screen technology\r\nRear camera 12 MP, f/1.6, 26mm (wide), 1.4µm, dual pixel PDAF, OIS\r\n12 MP, f/2.0, 52mm (telephoto), 1/3.4\", 1.0µm, PDAF, OIS, 2x optical zoom\r\n12 MP, f/2.4, 120˚, 13mm (ultrawide), 1/3.6\"\r\nTOF 3D LiDAR scanner (depth)\r\nFront camera 12 MP, f/2.2, 23mm (wide), 1/3.6\"\r\nSL 3D, (depth/biometrics sensor)\r\nApple A14 Bionic Chipset (5 nm)\r\nRAM capacity 6 GB', 1),
(15, 'Samsung Galaxy A32 ', '6250', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/s/a/samsung-galaxy-a32-17.jpg', 'Specifications\r\nScreen size 6.4 inches\r\nSuper AMOLED screen technology\r\nRear camera Wide-angle camera: 64 MP, f/1.8, 26mm, PDAF\r\nSuper wide-angle camera: 8 MP, f/2.2, 123˚, 1/4.0\", 1.12µm\r\nMacro camera: 5 MP, f/2.4\r\nDepth sensor: 5 MP, f/2.4\r\nFront camera 20 MP, f/2.2\r\nChipset Mediatek Helio G80 (12 nm)\r\nRAM capacity 6 GB', 1),
(16, 'Laptop Asus Zenbook UX425EA KI429T i5 1135G7/8GB/512GB SSD/Win 10', '23999', 'https://images.fpt.shop/unsafe/fit-in/240x215/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/2/18/637492369087666209_asus-zenbook-ux425-xam-dd-bh-2nam.jpg', 'Specifications\r\nCPU Intel Core i5-1135G7\r\nRAM 8 GB, LPDDR4X, 3200 MHz\r\nDisplay 14.0\", 1920 x 1080 Pixel, IPS, 60 Hz, 400 nits, Anti-glare LED-backlit\r\nIntel Iris Xe Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 1.17\r\nDimensions (mm) 319 x 208 x 13.9\r\nMade in China\r\nRelease year 2021', 2),
(17, 'Laptop Acer Nitro Gaming AN515 57 74RD i7 11800H/8GB/512GB SSD/Nvidia RTX 3050 4GB/Win10', '27999', 'https://images.fpt.shop/unsafe/fit-in/240x215/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/7/20/637624023473077758_acer-nitro-gaming-an515-57-den-rtx3050-dd-1.jpg', 'Specifications\r\nCPU Intel Core i7-11800H\r\nRAM 8 GB, DDR4, 3200 MHz\r\n15.6\" screen, 1920 x 1080 Pixel, IPS, 144 Hz, 255 - 260 nits, LED-backlit\r\nNVIDIA GeForce RTX 3050 4GB Graphics & Intel UHD Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.2\r\nDimensions (mm) 363.4 x 255 x 23.9\r\nMade in China\r\nRelease year 2021', 2),
(18, 'Laptop MSI Bravo 15 B5DD 060VN R5 5600H/8GB/512GB/RX5500M 4GB/15.6\"FHD/Win 10', '21999', 'https://images.fpt.shop/unsafe/fit-in/240x215/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/7/7/637612635561893293_msi-bravo-15-den-dd.jpg', 'Specifications\r\nCPU AMD Ryzen 5-5600H\r\nRAM 8 GB, DDR4, 3200 MHz\r\n15.6\" screen, 1920 x 1080 Pixel, IPS, 144 Hz, IPS LCD LED Backlit, True Tone\r\nGraphics AMD Radeon RX 5500M 4 GB\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.35\r\nDimensions (mm) 359 x 254 x 24.9\r\nMade in China\r\nRelease year 2021', 2),
(19, 'Laptop Asus TUF Gaming FX506LH HN002T i5 10300H/8GB/512GB SSD/GTX1650 4GB/Win10 ', '21999', 'https://images.fpt.shop/unsafe/fit-in/240x215/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/2/25/637498497194722384_FA506LH-LI-dd.jpg', 'Specifications\r\nCPU Intel Core i5-10300H\r\nRAM 8 GB, DDR4, 2933 MHz\r\n15.6\" screen, 1920 x 1080 Pixel, IPS, 144 Hz, 250 nits, IPS LCD LED Backlit, True Tone\r\nNVIDIA GeForce GTX 1650 4 GB Graphics & Intel UHD Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.3\r\nDimensions (mm) 359 x 256 x 24.7\r\nMade in China\r\nRelease year 2021', 2),
(20, 'Laptop Acer Aspire Gaming A715 75G 56ZL i5 10300H/8GB/512GB SSD/Nvidia GTX1650 4GB/Win10 ', '20999', 'https://images.fpt.shop/unsafe/fit-in/240x215/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/6/25/637602408859884086_acer-aspire-gaming-a715-42g-den-dd.jpg', 'Specifications\r\nCPU Intel Core i5-10300H\r\nRAM 8 GB, DDR4, 3200 MHz\r\n15.6\" display, 1920 x 1080 Pixel, IPS, 60 Hz, Acer ComfyView Anti-glare LED-backlit\r\nNVIDIA GeForce GTX 1650 4 GB Graphics & Intel UHD Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.1\r\nDimensions (mm) 363.4 x 254.5 x 22.9\r\nMade in China\r\nRelease year 2021', 2),
(21, 'Laptop Acer Aspire Gaming A715 42G R4ST R5 5500U/8GB/256GB SSD/Nvidia GTX1650 4GB/Win10', '19999', 'https://images.fpt.shop/unsafe/fit-in/240x215/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/6/25/637602408859884086_acer-aspire-gaming-a715-42g-den-dd.jpg', 'Specifications\r\nCPU AMD Ryzen 5-5500U\r\nRAM 8 GB, DDR4, 3200 MHz\r\n15.6\" display, 1920 x 1080 Pixel, IPS, 60 Hz, Acer ComfyView Anti-glare LED-backlit\r\nNVIDIA GeForce GTX 1650 4 GB Graphics & AMD Radeon Graphics\r\n256 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.1\r\nDimensions (mm) 363.4 x 254.5 x 22.9\r\nMade in China\r\nRelease year 2021', 2),
(22, 'Laptop Acer Nitro Gaming AN515 57 74NU i7 11800H/8GB/512GB SSD/RTX 3050Ti 4GB/Win10', '29999', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/acer-nitro-5-2021-fpt-26.jpg', 'Specifications\r\nCPU Intel Core i7-11800H\r\nRAM 8 GB, DDR4, 3200 MHz\r\n15.6\" display, 1920 x 1080 Pixel, IPS, 144 Hz, LED-backlit\r\nNVIDIA GeForce RTX 3050Ti Graphics 4 GB & Intel UHD Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.2\r\nDimensions (mm) 363.4 x 255 x 23.9\r\nMade in China\r\nRelease year 2021', 2),
(23, 'Laptop Dell Vostro V5410 i5 11300H/8GB/512GB/14.0\"FHD/Win 10', '22699', 'https://www.thegioididong.com/laptop/dell-vostro-5301-i5-c4vv92?src=osp', 'Specifications\r\nCPU Intel Core i5-11300H\r\nRAM 8 GB, DDR4, 3200 MHz\r\nDisplay 14.0\", 1920 x 1080 Pixel, WVA, 60 Hz, 250 nits, WVA Anti-glare LED Backlit Narrow Border\r\nIntel Iris Xe Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 1.4\r\nDimensions (mm) 321.5 x 212.4 x 18\r\nMade in China\r\nRelease year 2021', 2),
(24, 'Dell G3 15 3500B i7 10750H/16GB/512GB/15.6\"FHD/NV GTX1660Ti 6GB/Win 10', '31999', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/dell-g3-15-5.jpg', 'Specifications\r\nCPU Intel Core i7-10750H\r\n16 GB RAM, DDR4, 2933 MHz\r\n15.6\", 1920 x 1080 Pixel display, WVA, 120 Hz, 250 nits, WVA Anti-glare LED Backlit Narrow Border\r\nNVIDIA GeForce GTX 1660Ti 6 GB Graphics & Intel UHD Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.58\r\nDimensions (mm) 364.46 x 254 x 21.60 ~ 30.96\r\nMade in China\r\nRelease year 2020', 2),
(25, 'Laptop MSI Gaming GF65 10UE 286VN i5 10500H/16GB/512GB/15.6FHD/RTX 3060 Max-Q 6Gb/Win 10', '29199', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/MSI-Gaming-GF65-10UE-fpt-4.jpg', 'Specifications\r\nCPU Intel Core i5-10500H\r\n16 GB RAM, DDR4, 3200 MHz\r\n15.6\" screen, 1920 x 1080 Pixel, IPS, 144 Hz\r\nNVIDIA GeForce RTX 3060 Max-Q Graphics 6GB\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 1.86\r\nDimensions (mm) 359 x 254 x 21.7\r\nMade in China\r\nRelease year 2021', 2),
(26, 'Laptop Acer Swift 3 SF313 53 518Y i5 1135G7/16GB/512GB SSD/13.5\'\' QHD/Win10', '22999', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/acer-swift-3-sf313-53-bac-14.jpg', 'Specifications\r\nCPU Intel Core i5-1135G7\r\n16 GB RAM, LPDDR4X, 4266 MHz\r\nDisplay 13.5\", 2256 x 1504 Pixel, IPS, 60 Hz, Acer CineCrystalTM LED-backlit TFT LCD\r\nIntel Iris Xe Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 1.19\r\nDimensions (mm) 302.5 x 233.88 x 15.95\r\nMade in China\r\nRelease year 2020', 2),
(27, 'Laptop Asus Gaming TUF FX506HC HN002T i5 11400H/8GB/512GB SSD/RTX 3050_4GB/Win10', '25999', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/asus-tuf-gaming-f15-fx506-16.jpg', 'Specifications\r\nCPU Intel Core i5-11400H\r\nRAM 8 GB, DDR4, 2933 MHz\r\n15.6\" screen, 1920 x 1080 Pixel, IPS, 144 Hz, 250 nits, Anti-glare LED-backlit\r\nNVIDIA GeForce RTX 3050 4GB Graphics & Intel UHD Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.3\r\nDimensions (mm) 359 x 256 x 24.4\r\nMade in China\r\nRelease year 2021', 2),
(28, 'Laptop Dell Inspiron N3501 i3 1125G4/4GB/256GB/15.6\"FHD/Win 10', '15199', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/dell-inspiron-3501-1.jpg', 'Specifications\r\nCPU Intel Core i3-1125G4\r\nRAM 4 GB, DDR4, 2666 MHz\r\n15.6\" display, 1920 x 1080 Pixel, WVA, 60 Hz, 220 nits, WVA Anti-glare LED Backlit Narrow Border\r\nIntel UHD Graphics\r\n256 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 1.96\r\nDimensions (mm) 363.96 x 249 x 19.9\r\nMade in China\r\nRelease year 2021', 2),
(29, 'Laptop MSI Gaming GF63-468VN i5 10500H/8GB/512GB/GTX 1650 Max-Q 4GB/Win 10', '20999', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/msi-gf63-thin-7.jpg', 'Specifications\r\nCPU Intel Core i5-10500H\r\nRAM 8 GB, DDR4, 3200 MHz\r\n15.6\" screen, 1920 x 1080 Pixel, IPS, 144 Hz, 300 nits, IPS LCD LED Backlit, True Tone\r\nNVIDIA GeForce GTX 1650 Max-Q Graphics 4 GB & Intel UHD Graphics\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 1.86\r\nDimensions (mm) 359 x 254 x 21.7\r\nMade in China\r\nRelease year 2021', 2),
(30, 'Laptop MSI Bravo 15 B5DD 028VN R7 5800H/8GB/512GB SSD/15.6\"FHD/RX5500M 4Gb/Win10', '24999', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/MSI-Bravo-15-B5DD-11.JPG', 'Specifications\r\nCPU AMD Ryzen 7-5800H\r\nRAM 8 GB, DDR4, 3200 MHz\r\n15.6\" screen, 1920 x 1080 Pixel, IPS, 144 Hz\r\nGraphics AMD Radeon RX 5500M 4 GB\r\n512 GB SSD Hard Drive\r\nWindows 10 operating system\r\nWeight (kg) 2.35\r\nDimensions (mm) 359 x 258 x 25\r\nMade in China\r\nRelease year 2021', 2),
(31, 'Bluetooth AirPods 2 Apple ', '4390', 'https://cdn.tgdd.vn/Products/Images/54/236016/bluetooth-airpods-2-apple-mv7n2-imei-1-org.jpg', 'Specifications AirPods 2 Apple MV7N2 Bluetooth Headphones\r\n•	The battery:\r\nUse 5 hours - Charge 2 hours\r\n• Charging port:\r\nLightning\r\n•	Compatible:\r\nAndroidiOS (iPhone)\r\n• Connecting application:\r\nSiri\r\n•	Utilities:\r\nThere is a voice microphone\r\n• Controlled by:\r\nTouch sensor\r\n•	The firm\r\nApple', 3),
(32, 'Bluetooth True Wireless Hydrus ', '2750', 'https://cdn.tgdd.vn/Products/Images/54/238028/bluetooth-true-wireless-hydrus-ts12bc-hong-1-org.jpg', 'True Wireless headphones perfectly synchronize the color between the housing and the charging box, the housing design is small, friendly, and easy to wear and remove.\r\nThe charging box has a flat bottom surface, so it should be balanced on a shelf or table. The lid fits tightly to the body of the charging box, ensuring the safety of the headset when storing.\r\n\r\nSoft rounded ear tips, up to 3 pairs of different sizes for users to choose according to their ear size to ensure a tight Bluetooth headset, a better listening experience.\r\n\r\nCover your ears with authentic, vivid sound, strong and powerful bass', 3),
(33, 'Power bank polymer 10000 mAh Hydrus', '2200', 'https://cdn.tgdd.vn/Products/Images/57/245246/pin-polymer-10000-mah-hydrus-pa-ck01-xam-2-org.jpg', 'Specifications Polymer power bank 10,000 mAh Hydrus PA CK01\r\n• Charging performance:\r\nsixty four%\r\n•	Battery capacity:\r\n10,000 mAh\r\n• Time to fully charge the battery:\r\n10 - 11 hours (using Adapter 1A)5 - 6 hours (using Adapter 2A)\r\n•	Input:\r\nMicro USB: 5V - 2A\r\n•	Output power:\r\nUSB: 5V - 2.1A\r\n• Battery core:\r\nPolymers\r\n• Technology/Utilities:\r\nLED indicator light\r\n•	Size:\r\n1.5cm thick - 7.5cm wide - 14.5cm long\r\n•	Weight:\r\n233g\r\n• Trademarks of:\r\nMobile World\r\n•	Made in:\r\nChina\r\n•	The firm\r\nHydrus', 3),
(34, 'Bluetooth True Wireless Samsung Galaxy Buds+ R175', '1490', 'https://cdn.tgdd.vn/Products/Images/54/238231/bluetooth-tws-samsung-galaxy-bud-r175-1-org.jpg', 'Samsung Galaxy Buds+ R175 True Wireless Bluetooth Headset Specifications\r\n• Headphone time:\r\nUse 11 hours - Charge 2 hours\r\n• Charging box time:\r\nUse 22 hours - Charge 3 hours\r\n• Charging port:\r\nType-C\r\n• Audio technology:\r\nAmbient Sound\r\n•	Compatible:\r\nAndroidiOS (iPhone)Windows\r\n• Controlled by:\r\nTouch sensor\r\n•	The firm\r\nSamsung.', 3),
(35, 'Sony Extra Bass Bluetooth Speaker SRS-XB23', '2241', 'https://cdn.tgdd.vn/Products/Images/2162/225536/loa-bluetooth-sony-extra-bass-srs-xb23-den-1-org.jpg', 'Specifications Sony Extra Bass Bluetooth Speaker SRS-XB23\r\n•	Used Time:\r\nUse about 12 hours Charge about 4 hours\r\n•	Wireless Connectivity:\r\nBluetooth 5.0\r\n•	Utilities:\r\nWaterproof, dustproof IP67\r\n•	Control:\r\nBluetooth on/off Switch Songs Button BATT Test Battery Button Party Connect Button ST PAIR Play/pause music Volume up/down\r\n• Trademarks of:\r\nJapan\r\n•	The firm\r\nSony', 3),
(36, 'Power bank 7500 mAh AVA+ LJ JP199', '1990', 'https://cdn.tgdd.vn/Products/Images/57/240696/ava-lj-jp199-xanh-ngoc-1-org.jpg', 'Specifications Power bank 7500 mAh AVA+ LJ JP199\r\n• Charging performance:\r\nsixty four%\r\n•	Battery capacity:\r\n7,500 mAh\r\n• Time to fully charge the battery:\r\n7 - 8 hours (using Adapter 1A)3 - 4 hours (using Adapter 2A)\r\n•	Input:\r\nMicro USB: 5V - 2A\r\n•	Output power:\r\nUSB: 5V - 2.2A\r\n• Battery core:\r\nLi-Ion\r\n• Technology/Utilities:\r\nLED indicator light\r\n•	Size:\r\nLength 8.7 cm - Width 5.8 cm - Thickness 2 cm\r\n•	Weight:\r\n172 g\r\n• Trademarks of:\r\nMobile World\r\n•	Made in:\r\nChina', 3),
(37, 'Bluetooth True Wireless Samsung Galaxy Buds 2 R177N', '2990', 'https://cdn.tgdd.vn/Products/Images/54/248455/bluetooth-true-wireless-samsung-buds-2-r177n-trang-1-org.jpg', 'Samsung Galaxy Buds 2 R177N True Wireless Bluetooth Headset Specifications White\r\n• Headphone time:\r\nUse 7.5 hours - Charge 1.5 hours\r\n• Charging box time:\r\nUse 29 hours - Charge 1.5 hours\r\n• Charging port:\r\nType-C\r\n• Audio technology:\r\nActive Noise CancelingAmbient Sound\r\n•	Compatible:\r\nAndroidRam 1.5G or more\r\n• Connecting application:\r\nBluetooth TWS\r\n•	Utilities:\r\nIPX2 Waterproof With voice mic\r\n• Connection support:\r\nBluetooth 5.2\r\n• Controlled by:\r\nTouch sensor\r\n•	The firm\r\nSamsung', 3),
(38, 'Bluetooth AirPods Pro Wireless Charge Apple MWP22', '5635', 'https://cdn.tgdd.vn/Products/Images/54/236026/airpods-pro-wireless-charge-apple-mwp22-ava-1-org.jpg', 'Specifications AirPods Pro Wireless Charge Apple Bluetooth Headset MWP22\r\n• Headphone time:\r\nUse 4.5 hours - Charge 2 hours\r\n• Charging box time:\r\nUse 24 hours - Charge 3 hours\r\n• Charging port:\r\nLightning Wireless Charging\r\n• Audio technology:\r\nActive Noise CancellationAdaptive EQ\r\n•	Compatible:\r\nAndroidiOS (iPhone)\r\n•	Utilities:\r\nWater-resistant, Noise-cancelling, Voice-mic\r\n• Controlled by:\r\nTouch sensor\r\n•	The firm\r\nApple', 3),
(39, 'Bluetooth AirPods Max Apple MGYH3/ MGYJ3/ MGYL3', '13990', 'https://cdn.tgdd.vn/Products/Images/54/236331/bluetooth-airpods-max-apple-1-1-org.jpg', 'Specifications AirPods Max Apple Bluetooth Earphones MGYH3/ MGYJ3/ MGYL3\r\n•	The battery:\r\nUse 20 hours - Charge 3 hours\r\n• Charging port:\r\nLightning\r\n• Audio technology:\r\nSpatial AudioActive Noise CancellationAdaptive EQTransparency Mode\r\n•	Compatible:\r\nAndroidiOS (iPhone)\r\n•	Utilities:\r\nAnti-noise\r\n• Controlled by:\r\nPress key\r\n•	The firm\r\nApple.', 3),
(40, 'Bluetooth True Wireless LG Tone Free HBS-FN6', '3490', 'https://cdn.tgdd.vn/Products/Images/54/228895/tai-nghe-bluetooth-true-wireless-lg-hbs-fn6-den-1-1-org.jpg', 'Specifications LG Tone Free True Wireless Bluetooth Headset HBS-FN6\r\n• Headphone time:\r\nUse 6 hours - Charge 1 hour\r\n• Charging box time:\r\nUse 12 hours - Charge 2 hours\r\n• Charging port:\r\nType-C\r\n• Audio technology:\r\nHeadphone Spatial ProcessingMeridianMQA music streaming\r\n•	Compatible:\r\nAndroidiOS (iPhone)Windows\r\n• Connecting application:\r\nTONE Free\r\n•	Utilities:\r\nWater resistant Included ear cushions\r\n• Connection support:\r\nBluetooth 5.0\r\n• Controlled by:\r\nTouch sensor\r\n•	The firm\r\nLG.', 3),
(41, 'Tablet Samsung Galaxy Tab S7 FE', '13990', 'https://cdn.tgdd.vn/Products/Images/522/240254/samsung-galaxy-tab-s7-fe-green-1-org.jpg', 'Samsung Galaxy Tab S7 FE Tablet Configuration\r\nScreen:\r\n12.4\"TFT LCD\r\nOperating system:\r\nAndroid 11\r\nChips:\r\nSnapdragon 750G\r\nRAM:\r\n4 GB\r\nInternal memory:\r\n64 GB\r\nConnection:\r\nSupport 4GHaving calls\r\nSIM:\r\n1 Nano SIM\r\nRear camera:\r\n8 MP\r\nFront camera:\r\n5 MP\r\nRechargeable batteries:\r\n10090 mAh45 W\r\nThe firm\r\nSamsung.', 4),
(42, 'Tablet Huawei MatePad T10s', '5140', 'https://cdn.tgdd.vn/Products/Images/522/233257/huawei-matepad-t10s-5-org.jpg', 'Huawei MatePad T10s Tablet Configuration (Huawei Mobile Service Platform)\r\nScreen:\r\n10.1\" IPS LCD\r\nOperating system:\r\nAndroid 10 (Without Google)\r\nChips:\r\nKirin 710A\r\nRAM:\r\n3 GB\r\nInternal memory:\r\n64 GB\r\nConnection:\r\nSupport 4GHaving calls\r\nSIM:\r\n1 Nano SIM\r\nRear camera:\r\n5 MP\r\nFront camera:\r\n2 MP\r\nRechargeable batteries:\r\n5100 mAh10 W', 4),
(43, 'Tablet Samsung Galaxy Tab A7 Lite', '4490', 'https://cdn.tgdd.vn/Products/Images/522/237325/samsung-galaxy-tab-a7-lite-5-2-org.jpg', 'Samsung Galaxy Tab A7 Lite Tablet Configuration\r\n•	Screen:\r\n8.7\" TFT LCD\r\n•	Operating system:\r\nAndroid 11\r\n• Chips:\r\nMediaTek MT8768T 8 cores\r\n• RAM:\r\n3 GB\r\n•	Internal memory:\r\n32 GB\r\n•	Connection:\r\nSupport 4GHaving calls\r\n• SIM:\r\n1 Nano SIM\r\n• Rear camera:\r\n8 MP\r\n•	Front camera:\r\n2 MP\r\n•	Rechargeable batteries:\r\n5100 mAh15 W\r\n•	The firm\r\nSamsung', 4),
(44, 'Tablet Lenovo Tab M10 - FHD Plus', '5890', 'https://cdn.tgdd.vn/Products/Images/522/235365/lenovo-tab-m10-fhd-plus-1-1-org.jpg', 'configuration Tablet Lenovo Tab M10 - FHD Plus\r\n•	Screen:\r\n10.3\" IPS LCD\r\n•	Operating system:\r\nAndroid 9\r\n• Chips:\r\nMediaTek Helio P22T\r\n• RAM:\r\n4 GB\r\n•	Internal memory:\r\n64 GB\r\n•	Connection:\r\n4G Support\r\n• SIM:\r\n1 Nano SIM\r\n• Rear camera:\r\n8 MP\r\n•	Front camera:\r\n5 MP\r\n•	Rechargeable batteries:\r\n5000 mAh10 W', 4),
(45, 'Tablet Huawei MatePad 64GB', '6590', 'https://cdn.tgdd.vn/Products/Images/522/233258/huawei-matepad-5-org.jpg', 'Huawei MatePad 64GB Tablet Configuration (Huawei Mobile Service Platform)\r\n•	Screen:\r\n10.4\" IPS LCD\r\n•	Operating system:\r\nAndroid 10 (Without Google)\r\n• Chips:\r\nKirin 810\r\n• RAM:\r\n4 GB\r\n•	Internal memory:\r\n64 GB\r\n• Rear camera:\r\n8 MP\r\n•	Front camera:\r\n8 MP\r\n•	Rechargeable batteries:\r\n7250 mAh10 W', 4),
(46, 'Tablet Lenovo Tab M10 - Gen 2', '4690', 'https://cdn.tgdd.vn/Products/Images/522/235366/lenovo-tab-m10-gen-2-1-org.jpg', 'Configuration Tablet Lenovo Tab M10 - Gen 2\r\n•	Screen:\r\n10\" IPS LCD\r\n•	Operating system:\r\nAndroid 10\r\n• Chips:\r\nMediaTek Helio P22T\r\n• RAM:\r\n2 GB\r\n•	Internal memory:\r\n32 GB\r\n•	Connection:\r\nSupport 4GHaving calls\r\n• SIM:\r\n1 Nano SIM\r\n• Rear camera:\r\n8 MP\r\n•	Front camera:\r\n5 MP\r\n•	Rechargeable batteries:\r\n5000 mAh10 W', 4),
(47, 'iPad Pro M1 12.9 inch WiFi Cellular 256GB', '38490', 'https://cdn.tgdd.vn/Products/Images/522/238651/ipad-pro-m1-129-inch-wifi-cellular-256gb-2021-bac-org.jpg', 'Configuration iPad Pro M1 12.9-inch WiFi Cellular 256GB Tablet (2021)\r\n•	Screen:\r\n12.9\"Liquid Retina XDR mini-LED LCD\r\n•	Operating system:\r\niPadOS 14\"\r\n• Chips:\r\nApple M1 8 cores\r\n• RAM:\r\n8 GB\r\n•	Internal memory:\r\n256 GB\r\n•	Connection:\r\n5G Listen to FaceTime calls\r\n• SIM:\r\n1 Nano SIM or 1 eSIM\r\n• Rear camera:\r\n12 MP Main & 10 MP Sub, TOF 3D LiDAR\r\n•	Front camera:\r\n12 MP\r\n•	Rechargeable batteries:\r\n40.88 Wh (~ 10,835 mAh)20 W', 4),
(48, 'Tablet Samsung Galaxy Tab S6 Lite', '9990', 'https://cdn.tgdd.vn/Products/Images/522/219912/samsung-galaxy-tab-s6-lite-xanh-1-org.jpg', 'Samsung Galaxy Tab S6 Lite Tablet Configuration\n•	Screen:\n10.4\"PLS LCD\n•	Operating system:\nAndroid 10\n• Chips:\nExynos 9611\n• RAM:\n4 GB\n•	Internal memory:\n64 GB\n•	Connection:\nSupport 4GHaving calls\n• SIM:\n1 Nano SIM\n• Rear camera:\n8 MP\n•	Front camera:\n5 MP\n•	Rechargeable batteries:\n7040 mAh10 W\n•	The firm\nSamsung.', 4),
(49, 'Liquid Retina XDR 12.9', '25600', 'https://cdn.tgdd.vn/Products/Images/522/238649/ipad-pro-m1-129-inch-wifi-cellular-128gb-2021-xam-1-org.jpg', 'iPad Pro M1 12.9 inch 2021 has a Liquid Retina XDR screen, applying mini-LED technology with 10,000 customized bulbs that are 120 times smaller than the previous generation, so the brightness can be up to 1600 nits even outdoors. can be used well.\r\n\r\nThe 12.9-inch screen offers a more expansive view, a stunning HDR experience on a clear screen that makes every action more engaging and immersive over time.\r\n\r\nEvery detail on the screen comes to life with a 1,000,000:1 contrast ratio and advanced technologies like P3 wide color, True Tone and ProMotion that make the iPad Pro 12.9-inch 2021 shine spectacularly.\r\n\r\nEnjoy the iPad Pro 12.9 inch 2021 like a laptop with the help of professional accessories like keyboards, Apple Pencil or modern game controllers,... These versatile accessories are designed with to take your work, creativity and leisure hours to the next level.\r\n\r\nNote: Accessories such as keyboard and Apple Pencil are not included in the box.', 4),
(50, 'iPad Pro M1 12.9 inch WiFi 128GB', '30790', 'https://cdn.tgdd.vn/Products/Images/522/237699/ipad-pro-m1-129-inch-wifi-128gb-2021-1-org.jpg', 'Configuration iPad Pro M1 12.9-inch WiFi 128GB Tablet (2021)\r\n•	Screen:\r\n12.9\"Liquid Retina XDR mini-LED LCD\r\n•	Operating system:\r\niPadOS 14\"\r\n• Chips:\r\nApple M1 8 cores\r\n• RAM:\r\n8 GB\r\n•	Internal memory:\r\n128 GB\r\n•	Connection:\r\nListen to calls via FaceTime\r\n• Rear camera:\r\n12 MP Main & 10 MP Sub, TOF 3D LiDAR\r\n•	Front camera:\r\n12 MP\r\n•	Rechargeable batteries:\r\n40.88 Wh (~ 10,835 mAh)20 W', 4),
(51, 'Tablet Xiaomi Pad 5 256GB', '10490', 'https://cdn.tgdd.vn/Products/Images/522/250934/xiaomi-pad-5-grey-gc-org.jpg', 'Xiaomi Pad 5 256GB Tablet Configuration\r\n•	Screen:\r\n11\" IPS LCD\r\n•	Operating system:\r\nAndroid 11\r\n• Chips:\r\nSnapdragon 860 octa-core\r\n• RAM:\r\n6 GB\r\n•	Internal memory:\r\n256 GB\r\n• Rear camera:\r\n13 MP\r\n•	Front camera:\r\n8 MP\r\n•	Rechargeable batteries:\r\n8720 mAh33 W', 4),
(52, 'iphone 14', '10000', '52.jpg', 'product new', 1),
(55, 'iphone 15', '1500', '53.jpg', 'jhgjhghj', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sale_code`
--

CREATE TABLE `sale_code` (
  `id` int(11) NOT NULL,
  `name_sale` text COLLATE utf8_unicode_ci NOT NULL,
  `image_sale` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sale` text COLLATE utf8_unicode_ci NOT NULL,
  `out_of_date` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sale_code`
--

INSERT INTO `sale_code` (`id`, `name_sale`, `image_sale`, `sale`, `out_of_date`) VALUES
(1, '10 % off', 'https://www.cask.vn/wwwroot/resources/upload/9%20%C3%9D%20t%C6%B0%E1%BB%9Fng%20Promotion%20hay%20v%C3%A0%20hi%E1%BB%87u%20qu%E1%BA%A3%20cho%20c%E1%BB%ADa%20h%C3%A0ng%20b%C3%A1n%20l%E1%BA%BB1.jpg', 'Min. 0$ capped at 15$', 'Valid: 02.03.2022'),
(2, '8% coins cashback', 'https://img5.thuthuatphanmem.vn/uploads/2021/12/02/banner-sale-cuoi-nam_083649868.jpg', 'Spend 400$ capped at 200$', 'Valid: 02.03.2022'),
(3, 'All forms of payment', 'https://img5.thuthuatphanmem.vn/uploads/2021/12/02/banner-sale-hoan-tien_083650843.jpg', 'Freeship', 'Use in: 4 days'),
(4, 'Free shipping Xtra', 'https://demoda.vn/wp-content/uploads/2022/02/anh-sale-dep-556x600.jpg', 'Spend 100$', 'Valid: Unlimited'),
(5, 'Payment via e-wallet', 'https://demoda.vn/wp-content/uploads/2022/02/anh-sale-dep-nhat-600x600.png', 'Max 70%', 'Valid: 02.03.2022');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `username` text COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mobile` int(30) NOT NULL,
  `uid` text COLLATE utf8_unicode_ci NOT NULL,
  `token` text COLLATE utf8_unicode_ci NOT NULL,
  `status` int(2) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `address`, `username`, `password`, `mobile`, `uid`, `token`, `status`) VALUES
(1, 'my@gmail.com', '123 btx', 'm', '123', 123456, '', '', 0),
(2, 'nm@gmail.com', '123 btn', 'my', '123', 123456, '', '', 0),
(4, 'adm@gmail.com', '12 s', 'ad', '456789', 123, '3K3qqIICxLMOUWG6HHJq1Mw6FZ83', '', 0),
(5, 'btnm@gmail.com', '1245', 'btnm', '123456', 123, 'MOQrUiJuXShixRUhwnEY95lgr6y2', '', 0),
(6, 'game@gmail.com', '123 game', 'game', '456789', 123, 'GOXZBAAuz8d8CN29LX4rgl7Rnio2', 'dIZuwz8lQG2f360ZlODyhl:APA91bEZHxQT_ErW6BqOWTeS32NiVDdgVBSFMug2kGuXGALFmMLpXQWS9rIGGdldkgNlxojvdPXDuZQe556keX6TK7vYZmRMn-r_9qBwQG6evtWfm6xkbDD1DzeGEJJc3jJga_gmxkaH', 0),
(7, 'admin@gmail.com', '123 admin', 'administration', '123456', 123, 'HlvTJmefLtSJSllQCkaSvqXfy6s1', 'fpJMNpboQB206miVsw1hau:APA91bGs9us40XsDTyj945TK51Yn8tb5-zC7Mb4xq4uP11ECg7DWfKLeia9bxHBbpxZHWIbEE1z4X22H1C6kaBe98zvVRJRK0yHQfeKk-xtsv65wr89A4ULdIJm-j2qHI9CoVzaiAvao', 1),
(8, 'mypangao@gmail.com', '123', 'nam my', '123456', 123, 'YUVZ8qTSgWTbu6ZXqQjAXTqdagv1', 'fzQg4wE_TrGY1nPT6Y0_qe:APA91bFpqlivv1583jgM3j5yt0ZV9EJsZNh5C8TehxVcru7s_9haXvoj2-FZHu3gxpJRq7JNBWW7CramAqHVBl8NHFkbk9CWlnbXT3DsvM5yK5JsZfSgUBwoAtiz2mv8JRHpljscV0Es', 0),
(9, 'user6@gmail.com', '456 btn', 'user', '123456my', 123456789, 'hqh7bOoWzigKiR4OG05wOiZY2HI2', 'e633HLD9RYaIZaprt77h26:APA91bGzR_7R8fhApzgmg4xoUyuA_YUEQSh_oUERW42tmafXMGMhrdrjArH59h34lYUkbBrVfjdejJrA0XYY8ddOgrfAerxHUwOA1Usjksgrx8Gy6Jk6D5P1LYGaS6uZmELTuzg268Nw', 0),
(10, 'user7@gmail.com', '456 btn', 'user', '123456', 456, 'QWfEnHhhSdRQXVdR5xpvi7peo6W2', 'e633HLD9RYaIZaprt77h26:APA91bGzR_7R8fhApzgmg4xoUyuA_YUEQSh_oUERW42tmafXMGMhrdrjArH59h34lYUkbBrVfjdejJrA0XYY8ddOgrfAerxHUwOA1Usjksgrx8Gy6Jk6D5P1LYGaS6uZmELTuzg268Nw', 0),
(11, 'user33@gmail.com', '890 btn', 'user 34', '123456', 764592433, 'JyIU4CHH89Mg403ViTLy2bXcCdY2', 'e633HLD9RYaIZaprt77h26:APA91bGzR_7R8fhApzgmg4xoUyuA_YUEQSh_oUERW42tmafXMGMhrdrjArH59h34lYUkbBrVfjdejJrA0XYY8ddOgrfAerxHUwOA1Usjksgrx8Gy6Jk6D5P1LYGaS6uZmELTuzg268Nw', 0),
(12, 'user8@gmail.com', '248', 'user 5', '123456', 123456, 'BsvmfAut5hYYRnGHSbpg8ksZP8o1', 'e633HLD9RYaIZaprt77h26:APA91bGzR_7R8fhApzgmg4xoUyuA_YUEQSh_oUERW42tmafXMGMhrdrjArH59h34lYUkbBrVfjdejJrA0XYY8ddOgrfAerxHUwOA1Usjksgrx8Gy6Jk6D5P1LYGaS6uZmELTuzg268Nw', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `menu_navigation`
--
ALTER TABLE `menu_navigation`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sale_code`
--
ALTER TABLE `sale_code`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `menu_navigation`
--
ALTER TABLE `menu_navigation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT cho bảng `sale_code`
--
ALTER TABLE `sale_code`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
