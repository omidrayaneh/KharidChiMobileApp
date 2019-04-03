-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 26, 2018 at 01:51 PM
-- Server version: 5.7.23
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `caffefar_kharidchi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `owner_name` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `roul` varchar(11) COLLATE utf8_persian_ci NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `email`, `password`, `active`, `created_at`, `owner_name`, `roul`) VALUES
(1, 'omidrayaneh@gmail.com', '202cb962ac59075b964b07152d234b70', 1, '2018-07-03 11:25:04', 'روغن سرای امید', 'admin'),
(2, 'test@gmail.com', '202cb962ac59075b964b07152d234b70', 1, '2018-08-14 14:15:07', 'امیدرایانه', 'user'),
(3, 'yosefi@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 1, '2018-11-05 10:10:56', 'مرکز خرید خانواده', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `cost`
--

CREATE TABLE `cost` (
  `id` int(11) NOT NULL,
  `cost_value` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `minimum_cost` varchar(255) COLLATE utf8_persian_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `cost`
--

INSERT INTO `cost` (`id`, `cost_value`, `minimum_cost`) VALUES
(1, '3000', '80000');

-- --------------------------------------------------------

--
-- Table structure for table `details`
--

CREATE TABLE `details` (
  `id` int(11) NOT NULL,
  `id_products` int(11) DEFAULT NULL,
  `detail_name` varchar(15) COLLATE utf8_persian_ci NOT NULL,
  `detail_value` varchar(5000) COLLATE utf8_persian_ci NOT NULL,
  `detail_image` varchar(500) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `details`
--

INSERT INTO `details` (`id`, `id_products`, `detail_name`, `detail_value`, `detail_image`) VALUES
(1, 1, 'روغن پرس سرد', 'روغن گیری کلدپرس فشردن دانه بدون حرارات می باشد که روغن حاصله عاری از هر گونه افزودنی و یا نگهدارنده های شیمیایی است که در قدیم به روش عصاری معروف بوده است .  در روش پرس سرد مراحل روغن گیری به گونه ای است که می توان بیشترین مواد موثر همچون ویتامین E و اسیدهای چرب غیر اشباع یگانه و چندگانه را به همراه روغن از دانه استخراج نمود .  شرط برای پرس سرد بدون نقص دقیق ترین انتخاب دانه های روغنی است زیرا برای طعم خوب روغن این شرط ضروری است در طی این عملیات در روغن روان حرارت معادل زیر ۴۰ درجه سانتیگراد است . این گرمی تقریبا” به اندازه همان حرارتی است که دانه های روغنی در کشورهای موطن خود در اثر تابش حرارت آفتاب متحمل می باشد . روغن های پرس سرد تصفیه نشده طبیعی به دلیل طعم و بو رنگ فوق العاده از دیگر محصولات روغنی تصفیه شده روغنهای خنثی کاملا” مشخص و متمایز می باشند.  تاثیر اسیدهای چرب اشباع نشده به دلیل اینکه بنیان اصلی انرژی بدن بر سوخت و ساز چربی استوار است و سلول ها برای اینکه بتوانند به طور طبیعی و نرمال فعالیت داشته باشند احتیاج به چربیهای اشباع شده به چند اتصال مضاعف و چربیهای زنده غنی از الکترون حیاتی دارند که در اکثر روغنهایی که از  دانه گیاهان به روش کلد پرس فرآوری می شود به مقدار فراوان وجود دارد .این چربی ها حریصانه و مشتاقانه پروتئین ها و اکسیژن را جذب کرده و در کل سیستم بدن وارد می کند . این منبع انرژی همیشه آماده است در موارد نیاز فورا در اختیار بدن قرار گیرد نظیر روغن گردو، بذر کتان ،کنجد و غیره کلدپرس که طبیعی و عاری از هر گونه مواد شیمیایی است. استخراج روغن کنجد به روش پرس سرد باعث حفظ کیفیت خواص طبیعی آن می شود.', 'http://kharidchi.ir/android_sms/android_image/coldpress.jpg'),
(2, NULL, 'کره گیاهی', 'کره گیاهی ما، توسط دستگاه کره کیری و با فشار مستقیم به دانه های گیاهی اعم از پسته، بادام،فندق ،بادام زمینی و ... تهیه میشوند. در سال 2003 سازمان نظارت بر غذا و داروی امریکا ادعای فواید سلامتی برای کره بادام‌زمینی و برخی مغزهای گیاهی را تأیید کرد و اعلام کرد شواهد علمی نشان می‌دهد که خوردن بیشتر مغزهای گیاهی به مقدار  42 گرم در روز ( به‌عنوان بخشی از برنامه غذایی کم کلسترول و کم‌چربی اشباع‌شده) می‌تواند خطر بیماری‌های قلبی را کاهش دهد.', 'http://kharidchi.ir/android_sms/android_image/Butter.jpg'),
(3, NULL, 'ارده', 'ارده یا طحینه خوراکی‌ست که از دانه‌های کنجد تفت داده شده و کوبیده شده بدست می‌آید. از ارده در آشپزی لبنانی، ارمنی، یونانی، قبرسی، ایرانی، اسرائیلی، ترکی، عراقی، مغربی و بلغاری استفاده می‌شود. ارده به تنهایی به عنوان چاشنی یا به بخش اصلی حلوا ارده، حلواشکری، بابا غنوج و حمص استفاده می‌شود.(حمص از نخود پخته، روغن زیتون، ارده ، آبلیمو و سیر له شده درست می‌شود)\r\nارده خود به تنهایی یک مادهٔ غذایی کامل برای هر وعده غذایی بویژه صبحانه است. ارده را به طرق مختلفی می‌توان مصرف نمود. می‌توان آن را بصورت خالص و بدون هیچگونه افزودنی با نان مصرف کرد یا مواد شیرینی از قبیل: عسل، شیره اعم از شیره نبات، خرما، انگور و مربا به آن اضافه نمود. میزان افزودنی بسته به ذایقه افراد متفاوت است.\r\nاز ارده غذایی به نام اشکنه ارده تهیه می‌کنند. روش تهیه آن بدین صورت است که مقدار یک یا دو قاشق غذاخوری ارده، مقداری روغن (ترجیحاً حیوانی)، و مقدار لازم نمک و فلفل داخل ظرفی ریخته، به اندازه یک لیوان آب جوش به آن اضافه کرده و به آرامی مخلوط می‌کنند. مخلوط حاصل را با نان تلیت کرده، می‌خورند. اشکنه ارده غذای بسیار مناسبی برای وعدهٔ سحری می‌باشد.\r\nارده سرشار از اسیدهای چرب اشباع نشده است که اگر در معرض هوای گرم و نور قرار بگیرند، چربی آن اکسید شده و به اصطلاح فاسد می شود بنابراین کنجد و فرآورده های تهیه شده از آن باید در جای خشک، خنک و دور از نور آفتاب نگهداری شود.\r\nمصرف ارده فشار خون بالا را کاهش می‌دهد.\r\nارده مقوی و خون ساز بوده ، همچنین ضد پوکی استخوان و تقویت کننده قوای جنسی میباشد و کلسیم ارده مانند سایر محصولات کنجد سه برابر کلسیم موجود در شیر خالص میباشد.\r\nاز فواید ارده و عسل کمک به درمان روماتیسم، آرتروز و بیماری‌های مفصلی است. ارده سرشار از کلسیم است و مصرف آن برای سلامت و استحکام استخوانها مفید میباشد.\r\nمصرف ارده و عسل یک وعده غذایی بسیار مناسب برای فصل زمستان که بدن به انرژی بیشتری برای گرم کردن خود احتیاج دارد، است و باعث افزایش انرژی بدن میشود. همچنین مواد موجود در عسل باعث تقویت سیستم ایمنی بدن شده و از ابتلا به بسیاری بیماری ها پیشگیری میکند.\r\nمخلوط ارده و عسل به کاهش کلسترول بد خون کمک میکند و مصرف متعادل آن برای بیماران قلبی عروقی مفید میباشد.\r\nمصرف ارده و عسل به درمان و پیشگیری از کم خونی کمک میکند و غذایی بسیار مفید برای زنان و دختران است.\r\nاز خواص ارده و عسل برای کودکان بالا بردن قدرت یادگیری، تقویت حافظه، تامین انرژی مورد نیاز بدن و کمک به رشد بهتر است.\r\nاز ارده کنجد در طب سنتی در درمان اختلالات کبد و کلیه استفاده می شود.\r\nتقویت قوای جسمانی و نیروی جنسی از دیگر فواید مصرف ارده و عسل است.\r\nارده نرم کننده سفتی ها و ورم‌های سفت داخلی است و دمل ها را نیز نرم می‌کند و مسکن درد آن است، همچنین استفاده موضعی از آن نیز همین خاصیت را دارد .\r\nآنتی‌اکسیدان‌های فراوان موجود در این دو ماده غذایی، می‌توانند عاملی برای پیشگیری از سرطان در بدن محسوب شوند.\r\nبه طورکلی استفاده روزانه از ارده و عسل در رژیم غذایی، برای درمان و برطرف کردن بی‌حسی و کرختی مفید است. همچنین مصرف این دو ماده مفید غذایی با هم، باعث گرم شدن بدن شده و از این رو دارویی شفابخش برای تمامی بیماری‌های ناشی از سرد شدن مزاج بدن است.\r\nاز فواید ارده کنجد انرژی زا بودن آن می باشد، بنابراین دارای درصد بالایی کالری نیز می باشد، و چنانچه شما جزو افراد کم تحرک هستید که روزانه بیش از ۲۰ دقیقه پیاده روی منظم ندارید یا ورزش نمی کنید، بدانید که با خوردن این ماده مغذی، حتما چاقی و عوارض آن انتظارتان را می کشد.', 'http://kharidchi.ir/android_sms/android_image/Tahini.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `invoice_no` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `alert_status` int(1) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id`, `invoice_no`, `user_id`, `status`, `alert_status`, `create_at`, `modify_at`) VALUES
(1, 'kc-1001', 7, 1, 1, '2018-10-25 09:06:28', '2018-10-13 09:09:55'),
(2, 'kc-1002', 41, 1, 1, '2018-10-25 09:06:33', '2018-10-13 10:13:06'),
(3, 'kc-1003', 40, 1, 1, '2018-10-25 09:06:38', '2018-10-15 08:53:39'),
(4, 'kc-1004', 21, 1, 1, '2018-10-25 09:06:41', '2018-10-20 12:42:45'),
(5, 'kc-1005', 40, 1, 1, '2018-11-06 22:06:14', '2018-10-25 09:39:22'),
(6, 'kc-1006', 40, 1, 1, '2018-11-06 22:06:39', '2018-10-25 09:46:17'),
(7, 'kc-1007', 43, 0, 1, '2018-10-27 14:49:36', '2018-10-27 12:53:38'),
(8, 'kc-1008', 45, 0, 1, '2018-10-30 08:06:38', '2018-10-28 15:31:40'),
(9, 'kc-1009', 45, 0, 1, '2018-10-30 08:06:38', '2018-10-28 15:31:57'),
(10, 'kc-1010', 7, 1, 1, '2018-11-06 22:04:50', '2018-11-05 14:52:00'),
(11, 'kc-1011', 40, 1, 1, '2018-11-07 14:16:13', '2018-11-07 14:14:08'),
(12, 'kc-1012', 40, 1, 1, '2018-11-07 20:41:16', '2018-11-07 20:40:50'),
(13, 'kc-1013', 40, 1, 1, '2018-11-07 20:42:06', '2018-11-07 20:41:40'),
(14, 'kc-1014', 40, 1, 1, '2018-11-07 20:43:08', '2018-11-07 20:42:57'),
(15, 'kc-1015', 49, 1, 1, '2018-11-17 20:34:36', '2018-11-10 13:32:25'),
(16, 'kc-1016', 7, 0, 1, '2018-11-22 14:01:58', '2018-11-21 16:12:23'),
(17, 'kc-1017', 50, 0, 1, '2018-11-24 16:27:45', '2018-11-24 07:54:30');

-- --------------------------------------------------------

--
-- Table structure for table `invoice_items`
--

CREATE TABLE `invoice_items` (
  `id` int(11) NOT NULL,
  `invoice_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` varchar(20) COLLATE utf8_persian_ci NOT NULL,
  `discount` varchar(20) COLLATE utf8_persian_ci NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `invoice_items`
--

INSERT INTO `invoice_items` (`id`, `invoice_id`, `product_id`, `qty`, `price`, `discount`) VALUES
(1, 1, 1, 4, '38000', '2000'),
(2, 2, 1, 2, '38000', '2000'),
(3, 2, 8, 2, '10000', '0'),
(4, 3, 3, 2, '50000', '0'),
(5, 3, 1, 1, '38000', '2000'),
(6, 4, 11, 1, '15000', '0'),
(7, 5, 10, 9, '10000', '0'),
(8, 6, 1, 1, '38000', '2000'),
(9, 7, 1, 1, '38000', '2000'),
(10, 8, 3, 2, '45000', '0'),
(11, 8, 1, 1, '38000', '2000'),
(12, 9, 3, 4, '45000', '0'),
(13, 10, 8, 1, '10000', '0'),
(14, 10, 1, 2, '38000', '0'),
(15, 11, 43, 1, '74500', '0'),
(16, 12, 1, 2, '38000', '0'),
(17, 13, 43, 1, '74500', '0'),
(18, 14, 43, 1, '74500', '0'),
(19, 15, 1, 5, '38000', '0'),
(20, 16, 1, 3, '38000', '0'),
(21, 17, 1, 1, '50000', '1000'),
(22, 17, 3, 1, '45000', '0'),
(23, 17, 18, 1, '12000', '0'),
(24, 17, 19, 1, '16000', '0'),
(25, 17, 21, 1, '23000', '0'),
(26, 17, 43, 1, '74500', '0');

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE `owner` (
  `id` int(11) NOT NULL,
  `owner_name` varchar(20) COLLATE utf8_persian_ci NOT NULL,
  `status` int(1) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`id`, `owner_name`, `status`, `create_at`) VALUES
(1, 'روغن سرای امید', 1, '2018-06-21 05:18:53'),
(2, 'امیدرایانه', 1, '2018-08-18 07:15:14'),
(3, 'مرکز خرید خانواده', 1, '2018-11-05 06:33:18');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id_product` int(11) NOT NULL,
  `name_product` varchar(50) NOT NULL,
  `status_product` int(1) NOT NULL,
  `admin_activation` int(1) NOT NULL DEFAULT '1',
  `newest` int(1) NOT NULL DEFAULT '0',
  `first_page` int(1) NOT NULL DEFAULT '0',
  `price` varchar(15) NOT NULL DEFAULT '0',
  `qty` varchar(11) NOT NULL DEFAULT '0',
  `discount` varchar(10) NOT NULL DEFAULT '0',
  `count_product` int(10) NOT NULL DEFAULT '10',
  `image_url` varchar(1000) NOT NULL,
  `web_image` varchar(1000) NOT NULL,
  `create_at_product` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_at_product` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `product_category_id` int(11) NOT NULL,
  `owner_id` int(255) DEFAULT NULL,
  `owner_id_` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id_product`, `name_product`, `status_product`, `admin_activation`, `newest`, `first_page`, `price`, `qty`, `discount`, `count_product`, `image_url`, `web_image`, `create_at_product`, `modify_at_product`, `product_category_id`, `owner_id`, `owner_id_`) VALUES
(1, 'روغن کنجد یک لیتری', 1, 1, 1, 1, '50000', '1', '1000', 0, 'http://kharidchi.ir/admin/uploads/cd717ecba2221e659c0cf0965c3e9346.jpg', 'uploads/cd717ecba2221e659c0cf0965c3e9346.jpg', '2018-07-10 12:12:45', '2018-11-22 14:02:31', 4, 1, 1),
(2, 'روغن آفتابگردان یک لیتری', 0, 0, 0, 0, '53000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/ebd556e6dfc99dbed29675ce1c6c68e5.jpg', 'uploads/ebd556e6dfc99dbed29675ce1c6c68e5.jpg', '2018-07-10 16:39:37', '2018-10-10 07:18:46', 4, 1, 1),
(3, 'روغن زیتون یک لیتری', 1, 1, 1, 1, '45000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/3176b78ed259040575aa2e035e264f83.jpg', 'uploads/3176b78ed259040575aa2e035e264f83.jpg', '2018-07-10 16:40:05', '2018-10-21 06:51:11', 4, 1, 1),
(4, 'روغن نارگیل 60 سی سی', 1, 1, 0, 0, '10000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/763b8741d9f1a6fb4e0b72865d993f33.jpg', 'uploads/763b8741d9f1a6fb4e0b72865d993f33.jpg', '2018-07-10 16:40:56', '2018-10-21 06:52:06', 4, 1, 1),
(5, ' روغن هسته آلبالو 60 سی سی', 1, 1, 1, 1, '15000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/97c50dd54c4ad0e75dfdc0e9089f2468.png', 'uploads/97c50dd54c4ad0e75dfdc0e9089f2468.png', '2018-07-10 16:42:10', '2018-08-16 14:04:34', 4, 1, 1),
(6, 'روغن کرچک 60 سی سی', 1, 1, 0, 0, '10000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/cf04c8a4def61c25176f825767d604e8.jpg', 'uploads/cf04c8a4def61c25176f825767d604e8.jpg', '2018-07-10 16:46:57', '2018-10-21 06:51:44', 4, 1, 1),
(7, 'روغن فندق 60 سی سی', 1, 1, 0, 0, '15000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/a003cb975bdedd1d2619f2bf47d1dcc3.jpg', 'uploads/a003cb975bdedd1d2619f2bf47d1dcc3.jpg', '2018-07-10 16:48:59', '2018-08-16 14:07:11', 4, 1, 1),
(8, 'روغن سیاهدانه 60 سی سی', 1, 1, 1, 1, '10000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/faccce5ca8397553ec27bcadb3e2e5fa.jpg', 'uploads/faccce5ca8397553ec27bcadb3e2e5fa.jpg', '2018-07-10 16:49:42', '2018-08-16 14:07:31', 4, 1, 1),
(9, 'روغن کتان یک لیتری', 0, 0, 0, 0, '35000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/aa5ebf55b642629f7e2dc66ed4686db1.jpg', 'uploads/aa5ebf55b642629f7e2dc66ed4686db1.jpg', '2018-07-10 16:52:02', '2018-10-10 07:19:10', 4, 1, 1),
(10, 'روغن بادام تلخ 60 سی سی', 1, 1, 0, 0, '10000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/c9f82df0f7d0adb0fe0ee6a9731057ed.jpg', 'uploads/c9f82df0f7d0adb0fe0ee6a9731057ed.jpg', '2018-07-10 16:52:49', '2018-08-16 14:08:07', 4, 1, 1),
(11, 'روغن بادام شیرین 60 سی سی', 1, 1, 0, 0, '15000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/c8f91cd4a58e6c96721f068dd1a1c6a5.jpg', 'uploads/c8f91cd4a58e6c96721f068dd1a1c6a5.jpg', '2018-07-10 16:53:13', '2018-08-16 14:08:16', 4, 1, 1),
(12, 'کره بادام زمینی نیم کیلو', 0, 0, 0, 0, '10000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/4652b19e09ced75df510bf5a263a2bfe.jpg', 'uploads/4652b19e09ced75df510bf5a263a2bfe.jpg', '2018-07-10 16:54:52', '2018-10-10 07:38:03', 5, 1, 1),
(13, 'کره فندق 200 گرمی', 0, 0, 1, 1, '19000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/470c1855684aabb9461c10c1bb38208e.jpg', 'uploads/470c1855684aabb9461c10c1bb38208e.jpg', '2018-07-10 16:55:47', '2018-10-10 07:19:28', 5, 1, 1),
(14, 'کره بادام 200 گرمی', 0, 0, 0, 0, '21000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/0381d0604e218c33afd52bc9e26006c3.jpeg', 'uploads/0381d0604e218c33afd52bc9e26006c3.jpeg', '2018-07-10 16:56:46', '2018-10-10 07:21:11', 5, 1, 1),
(15, 'کره گردو 200 گرمی', 0, 0, 1, 1, '20000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/e814176b9d90dde3b931754ab09e3cb7.jpg', 'uploads/e814176b9d90dde3b931754ab09e3cb7.jpg', '2018-07-10 16:57:42', '2018-10-10 07:20:52', 5, 1, 1),
(16, 'کره پسته 200 گرمی', 0, 0, 0, 0, '36000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/6ad9f55dfe8d9b5045aa1192ce460628.jpg', 'uploads/6ad9f55dfe8d9b5045aa1192ce460628.jpg', '2018-07-10 16:58:43', '2018-10-10 07:21:29', 5, 1, 1),
(17, 'کره چهار مغز 400 گرمی', 0, 0, 1, 1, '48000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/2f3e238e2a8d0bfdd8c27808b4f43ffa.jpg', 'uploads/2f3e238e2a8d0bfdd8c27808b4f43ffa.jpg', '2018-07-10 17:00:16', '2018-10-10 07:20:13', 5, 1, 1),
(18, 'ارده 250 گرمی', 1, 1, 0, 1, '12000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/23e53294c50deaf0c982a53a949b6179.jpg', 'uploads/23e53294c50deaf0c982a53a949b6179.jpg', '2018-07-10 17:01:51', '2018-10-25 07:16:10', 6, 1, 1),
(19, 'ارده 400 گرمی', 1, 1, 0, 1, '16000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/23e53294c50deaf0c982a53a949b6179.jpg', 'uploads/23e53294c50deaf0c982a53a949b6179.jpg', '2018-07-10 17:03:31', '2018-10-25 07:18:45', 6, 1, 1),
(20, 'ارده 700 گرمی', 1, 1, 0, 1, '28000', '1', '0', 0, 'http://kharidchi.ir/admin/uploads/23e53294c50deaf0c982a53a949b6179.jpg', 'uploads/23e53294c50deaf0c982a53a949b6179.jpg', '2018-07-10 17:04:01', '2018-10-25 07:18:59', 6, 1, 1),
(21, 'جا شمعی', 1, 1, 1, 1, '23000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/ea4a5d67991b6cbdc9f0c88093f19b64.jpg', 'uploads/ea4a5d67991b6cbdc9f0c88093f19b64.jpg', '2018-11-05 06:36:11', '2018-11-05 06:36:11', 8, 3, 3),
(22, 'شکر ریز با نمکدان', 1, 1, 1, 1, '65000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/47b62e9e694dd031daed6eff0bddad73.jpg', 'uploads/47b62e9e694dd031daed6eff0bddad73.jpg', '2018-11-05 06:45:39', '2018-11-05 07:02:57', 8, 3, 3),
(23, 'آبلیمو و ادویه جفتی', 1, 1, 1, 1, '126000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/057a91846b83a45706a3b200b974dfde.jpg', 'uploads/057a91846b83a45706a3b200b974dfde.jpg', '2018-11-05 06:57:47', '2018-11-05 07:03:14', 8, 3, 3),
(24, 'پیاله 5 تایی سرامیکی', 1, 1, 1, 1, '126000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/c57b509ddf3f1b09c6ab18f92f44ff8f.jpg', 'uploads/c57b509ddf3f1b09c6ab18f92f44ff8f.jpg', '2018-11-05 07:00:19', '2018-11-05 07:03:29', 8, 3, 3),
(25, 'قوری کدو ژوانی', 1, 1, 1, 1, '44000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/f05ef6c2a871f0145d2f81137a4857fe.jpg', 'uploads/f05ef6c2a871f0145d2f81137a4857fe.jpg', '2018-11-05 07:01:00', '2018-11-05 07:51:25', 8, 3, NULL),
(26, 'اردو خوری 4 تایی', 1, 1, 1, 1, '212000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/1323dc01b76d35a0ceae18b9ad220cbe.jpg', 'uploads/1323dc01b76d35a0ceae18b9ad220cbe.jpg', '2018-11-05 07:08:09', '2018-11-05 21:09:31', 8, 3, NULL),
(27, 'شیرینی خوری 2 طبقه', 1, 1, 1, 1, '151000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/b3a81e67abc9d7f9f3d9eae2fe7148cf.jpg', 'uploads/b3a81e67abc9d7f9f3d9eae2fe7148cf.jpg', '2018-11-05 07:08:55', '2018-11-05 07:09:34', 8, 3, NULL),
(28, 'کیک خوری هلالی', 1, 1, 1, 1, '272000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/ca7aa6989e955c1eab91ed20ed8eef73.jpg', 'uploads/ca7aa6989e955c1eab91ed20ed8eef73.jpg', '2018-11-05 21:06:53', '2018-11-05 22:16:33', 8, 3, NULL),
(29, 'قابلمه چینی در شیشه ای', 1, 1, 1, 1, '79000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/894b2f08a2589b6df2f39173fcd09a8f.jpg', 'uploads/894b2f08a2589b6df2f39173fcd09a8f.jpg', '2018-11-05 21:23:52', '2018-11-05 22:02:57', 11, 3, NULL),
(30, 'قابلمه چینی در پیرکس', 1, 1, 1, 1, '89000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/84bd966fdec85a72726ee7e193f565df.jpg', 'uploads/84bd966fdec85a72726ee7e193f565df.jpg', '2018-11-05 21:25:15', '2018-11-05 22:03:04', 11, 3, NULL),
(31, 'قابلمه چینی در پیرکس کوتاه', 1, 1, 1, 1, '110000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/d61e00688794e6ad04a890b945e00400.jpg', 'uploads/d61e00688794e6ad04a890b945e00400.jpg', '2018-11-05 21:26:06', '2018-11-05 22:03:13', 11, 3, NULL),
(32, 'قابلمه چینی', 1, 1, 1, 1, '80000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/531c358546e590952f1e5ae96958c593.jpg', 'uploads/531c358546e590952f1e5ae96958c593.jpg', '2018-11-05 21:26:58', '2018-11-05 22:03:24', 11, 3, NULL),
(33, 'قابلمه چینی با در چینی و قاشق', 1, 1, 1, 1, '118000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/c4912af03a678c3549027609a79c45a1.jpg', 'uploads/c4912af03a678c3549027609a79c45a1.jpg', '2018-11-05 21:28:08', '2018-11-05 22:03:33', 11, 3, NULL),
(34, 'قابلمه چینی با درچینی بزرگ', 1, 1, 1, 1, '125000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/e573cc708fad29a367595747833bd199.jpg', 'uploads/e573cc708fad29a367595747833bd199.jpg', '2018-11-05 21:28:58', '2018-11-05 22:02:50', 11, 3, NULL),
(35, 'سرویس سرخ پوستی 3 تایی', 1, 1, 1, 1, '171000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/4b2e31e7878a4e6f380b931876c6867f.jpg', 'uploads/4b2e31e7878a4e6f380b931876c6867f.jpg', '2018-11-05 21:30:03', '2018-11-05 21:59:13', 8, 3, NULL),
(36, 'Bosch چای ساز ', 0, 0, 0, 0, 'بزودی', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/83866a81ab575ceb5abf702d9c5c7443.jpg', 'uploads/83866a81ab575ceb5abf702d9c5c7443.jpg', '2018-11-05 21:33:51', '2018-11-06 12:23:24', 13, 3, NULL),
(37, 'آبلیمو خوری دوجداره', 1, 1, 1, 1, '35600', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/e8fdcc992f83a8bf60ac3a32f77cb237.jpg', 'uploads/e8fdcc992f83a8bf60ac3a32f77cb237.jpg', '2018-11-05 21:35:24', '2018-11-05 21:58:53', 8, 3, NULL),
(38, 'بانکه ناوارا ساز 2', 1, 1, 1, 1, '16000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/48244854bbb3a565efeb7bb5de07ef41.jpg', 'uploads/48244854bbb3a565efeb7bb5de07ef41.jpg', '2018-11-05 21:36:23', '2018-11-05 22:04:04', 12, 3, NULL),
(39, 'بانکه ناوارا ساز 3', 1, 1, 1, 1, '9000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/5cdc8bc201dc6596e9562773b06685ec.jpg', 'uploads/5cdc8bc201dc6596e9562773b06685ec.jpg', '2018-11-05 21:36:47', '2018-11-05 22:04:11', 12, 3, NULL),
(40, 'ادویه چهارگوش زیباسازان', 1, 1, 1, 1, '65000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/57edbc82da320fb4830b8c9d264f2618.jpg', 'uploads/57edbc82da320fb4830b8c9d264f2618.jpg', '2018-11-05 21:38:10', '2018-11-05 22:04:22', 12, 3, NULL),
(41, 'عسل خوری گلدار', 1, 1, 1, 1, '21000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/aa995bab31dfb438ef6e90d724dc20c2.jpg', 'uploads/aa995bab31dfb438ef6e90d724dc20c2.jpg', '2018-11-05 21:39:08', '2018-11-05 22:04:35', 8, 3, NULL),
(42, 'جای ادویه کیچن', 1, 1, 1, 1, '81000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/322c1594475319d25da3a5dc6bbde8c8.jpg', 'uploads/322c1594475319d25da3a5dc6bbde8c8.jpg', '2018-11-05 21:41:33', '2018-11-05 22:04:45', 12, 3, NULL),
(43, 'گیلاس خیاری ورکشاپ 6 تایی', 1, 1, 1, 1, '74500', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/7f06b22e9802dd622c216120c04d75ca.jpg', 'uploads/7f06b22e9802dd622c216120c04d75ca.jpg', '2018-11-05 21:43:21', '2018-11-05 22:00:15', 9, 3, NULL),
(44, 'لیوان بنفش 6 تایی', 1, 1, 1, 1, '63000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/ca42c32cb35e7803245c133db87adbe9.jpg', 'uploads/ca42c32cb35e7803245c133db87adbe9.jpg', '2018-11-05 21:43:59', '2018-11-05 22:00:25', 9, 3, NULL),
(45, 'گیلاس ویلاین 6 تایی', 1, 1, 1, 1, '116000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/3e44e00ef69f82f5b5fb6fa9deee6bd7.jpg', 'uploads/3e44e00ef69f82f5b5fb6fa9deee6bd7.jpg', '2018-11-05 21:44:47', '2018-11-05 22:00:34', 9, 3, NULL),
(46, 'گیلاس تپل پایه سبز', 1, 1, 1, 1, '54000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/deaca614fd22af54f2eb3ed9041aaffa.jpg', 'uploads/deaca614fd22af54f2eb3ed9041aaffa.jpg', '2018-11-05 21:45:34', '2018-11-05 22:00:48', 9, 3, NULL),
(47, 'گیلاس تولیپ 370', 1, 1, 1, 1, '128000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/de62dff942f9b1f0c9048eed4ab7f1c5.jpg', 'uploads/de62dff942f9b1f0c9048eed4ab7f1c5.jpg', '2018-11-05 21:47:19', '2018-11-05 22:00:56', 9, 3, NULL),
(48, 'گیلاس بیسترو 290', 1, 1, 1, 1, '81000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/d2fc81e30f1ea8384a6b9170c60cce87.jpg', 'uploads/d2fc81e30f1ea8384a6b9170c60cce87.jpg', '2018-11-05 21:48:02', '2018-11-05 22:01:05', 9, 3, NULL),
(49, 'بستنی خوری قیفی کوتاه', 1, 1, 1, 1, '73000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/99b648a4a7fe6e92021804272325bf9b.jpg', 'uploads/99b648a4a7fe6e92021804272325bf9b.jpg', '2018-11-05 21:48:51', '2018-11-05 22:01:26', 8, 3, NULL),
(50, 'لیوان گراندا ورکشاپ', 1, 1, 1, 1, '76500', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/3b8fa55c82a498fc0d6b00bcd01679b8.jpg', 'uploads/3b8fa55c82a498fc0d6b00bcd01679b8.jpg', '2018-11-05 21:49:53', '2018-11-05 22:01:42', 9, 3, NULL),
(51, 'لیوان جنیوسیس 6 رنگ', 1, 1, 1, 1, '97000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/a15c74105b19243b874eee5dec52ec08.jpg', 'uploads/a15c74105b19243b874eee5dec52ec08.jpg', '2018-11-05 21:51:02', '2018-11-05 22:01:51', 9, 3, NULL),
(52, 'Petek لیوان ساده ', 1, 1, 1, 1, '69000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/ee3e24dc206d0113523f64bd4e613493.jpg', 'uploads/ee3e24dc206d0113523f64bd4e613493.jpg', '2018-11-05 21:52:45', '2018-11-05 22:01:58', 9, 3, NULL),
(53, 'نیم لیوان جنیوسیس', 1, 1, 1, 1, '71000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/cbad3510e219e7f54233e9ed03e8aa1f.jpg', 'uploads/cbad3510e219e7f54233e9ed03e8aa1f.jpg', '2018-11-05 21:53:34', '2018-11-05 22:02:07', 9, 3, NULL),
(54, '1.5 lit فلاسک شیشه ای ', 1, 1, 1, 1, '83000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/1db452fc7da1ac2af19027d36ed5e272.jpg', 'uploads/1db452fc7da1ac2af19027d36ed5e272.jpg', '2018-11-05 21:55:01', '2018-11-05 22:02:29', 10, 3, NULL),
(55, 'پیاز خرد کن', 1, 1, 1, 1, '55000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/8d89b296c5e36f4662909331e5064c34.jpg', 'uploads/8d89b296c5e36f4662909331e5064c34.jpg', '2018-11-05 21:56:05', '2018-11-05 22:05:36', 14, 3, NULL),
(56, 'مربا خوری کد : 81786', 1, 1, 1, 1, '144000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/13264068d108c6901b3592ea654fcd57.jpg', 'uploads/13264068d108c6901b3592ea654fcd57.jpg', '2018-11-12 14:30:46', '2018-11-13 13:40:17', 8, 3, NULL),
(57, 'کلمن با فنجان ونعلبکی کد:73073', 1, 1, 1, 1, '0', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/97daf506aa922ccdd7a71eff0d9d16a7.jpg', 'uploads/97daf506aa922ccdd7a71eff0d9d16a7.jpg', '2018-11-12 14:44:56', '2018-11-12 14:45:25', 10, 3, NULL),
(58, 'کلمن مد گلابی کد :33785', 1, 1, 1, 1, '448000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/d8af0f891e22a5d5d07b1c5be8b10967.jpg', 'uploads/d8af0f891e22a5d5d07b1c5be8b10967.jpg', '2018-11-12 15:22:02', '2018-11-12 15:22:40', 10, 3, NULL),
(59, 'روغن مایع غنچه 2.7 کد: 90243', 1, 1, 1, 1, '20000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/19a1d4e6b77696c8e4545734a075b8ad.jpg', 'uploads/19a1d4e6b77696c8e4545734a075b8ad.jpg', '2018-11-13 15:09:01', '2018-11-13 15:09:31', 16, 3, NULL),
(60, 'روغن آفتابگردان اویلا 1.8 کد : 00445', 1, 1, 1, 1, '15200', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/e3d55c7a82769a050b671a2c902f8cf3.jpg', 'uploads/e3d55c7a82769a050b671a2c902f8cf3.jpg', '2018-11-13 16:22:14', '2018-11-13 16:59:04', 16, 3, NULL),
(61, 'روغن ذرت اویلا کد:03209', 1, 1, 1, 1, '39500', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/e3e0848e55bc2c47e69624fca1a44394.jpg', 'uploads/e3e0848e55bc2c47e69624fca1a44394.jpg', '2018-11-13 16:23:33', '2018-11-13 17:00:17', 16, 3, NULL),
(62, 'روغن آفتابگردان اویلا 810 کد : 80206', 1, 1, 1, 1, '7000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/b7af4f964073913dc77199495312bd09.jpg', 'uploads/b7af4f964073913dc77199495312bd09.jpg', '2018-11-13 16:26:29', '2018-11-13 17:00:29', 16, 3, NULL),
(63, 'روغن غنی شده فامیلا کد : 00902', 1, 1, 1, 1, '11500', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/f9b6f91b58b6e65d97b97d109a71c7db.jpg', 'uploads/f9b6f91b58b6e65d97b97d109a71c7db.jpg', '2018-11-13 16:28:12', '2018-11-13 17:00:36', 16, 3, NULL),
(64, 'روغن زیتون نیم لیتر کد : 77301', 1, 1, 1, 1, '35000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/beda24a99fc80ffb3a4dfe3d1fc235db.jpg', 'uploads/beda24a99fc80ffb3a4dfe3d1fc235db.jpg', '2018-11-13 16:30:32', '2018-11-13 17:00:43', 16, 3, NULL),
(65, 'روغن زیتون اسپانیا کد:11016', 1, 1, 1, 1, '42000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/061677eaa23004548c9cc7cc96de3327.jpg', 'uploads/061677eaa23004548c9cc7cc96de3327.jpg', '2018-11-13 16:45:26', '2018-11-13 17:00:49', 16, 3, NULL),
(66, 'روغن زیتون بکر اویلا کد:00376', 1, 1, 1, 1, '38200', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/f6fb54313ae7db15af3b077dea6c517f.jpg', 'uploads/f6fb54313ae7db15af3b077dea6c517f.jpg', '2018-11-13 16:46:39', '2018-11-13 17:00:56', 16, 3, NULL),
(67, 'روغن زیتون 250 اویلا کد:00352', 1, 1, 1, 1, '18200', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/febc415dc8cc9b87a5f16afb75c01870.jpg', 'uploads/febc415dc8cc9b87a5f16afb75c01870.jpg', '2018-11-13 16:47:44', '2018-11-13 17:01:03', 16, 3, NULL),
(68, 'روغن زرت 1 لیتر کد : 73003', 1, 1, 1, 1, '27000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/150630fb8b0dd2bfdc031bf1c7a306b3.jpg', 'uploads/150630fb8b0dd2bfdc031bf1c7a306b3.jpg', '2018-11-13 16:50:04', '2018-11-13 17:01:09', 16, 3, NULL),
(69, 'روغن زیتون 1 لیتر الیوا کد:21138', 1, 1, 1, 1, '58000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/267eada2454958a923c4fb103b4ebd3a.jpg', 'uploads/267eada2454958a923c4fb103b4ebd3a.jpg', '2018-11-13 16:51:18', '2018-11-13 17:01:15', 16, 3, NULL),
(70, 'روغن زیتون جیوا کد:00937', 1, 1, 1, 1, '37000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/23ef303a645f67771a1bc7ae3a2cba01.jpg', 'uploads/23ef303a645f67771a1bc7ae3a2cba01.jpg', '2018-11-13 16:53:21', '2018-11-13 17:01:21', 16, 3, NULL),
(71, 'روغن کانولا 810 غنچه کد:20189', 1, 1, 1, 1, '6800', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/578c43799b4778dbc455e04cbd1ae85e.jpg', 'uploads/578c43799b4778dbc455e04cbd1ae85e.jpg', '2018-11-13 16:54:45', '2018-11-13 17:01:27', 16, 3, NULL),
(72, 'روغن سرخ کردنی غنچه کد:01775', 1, 1, 1, 1, '13600', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/4a9067d0a76c7969a1b62160115d75a6.jpg', 'uploads/4a9067d0a76c7969a1b62160115d75a6.jpg', '2018-11-13 16:55:58', '2018-11-13 17:01:34', 16, 3, NULL),
(73, 'روغن بدون پالم اویلا کد:00223', 1, 1, 1, 1, '12000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/cb4b278b6585a0b889b57cae64029feb.jpg', 'uploads/cb4b278b6585a0b889b57cae64029feb.jpg', '2018-11-13 16:57:09', '2018-11-13 17:01:55', 16, 3, NULL),
(74, 'روغن زیتون 5 لیتر ایتالیکو', 1, 1, 1, 1, '135000', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/e422f6bee7318e7a2578f921b231b943.jpg', 'uploads/e422f6bee7318e7a2578f921b231b943.jpg', '2018-11-13 17:04:05', '2018-11-13 17:16:29', 16, 3, NULL),
(75, 'روغن جامد غنچه', 1, 1, 1, 1, '32500', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/a5eedf695fc37c3ae30134290fa8663f.jpg', 'uploads/a5eedf695fc37c3ae30134290fa8663f.jpg', '2018-11-13 17:05:20', '2018-11-13 17:16:01', 17, 3, NULL),
(76, 'روغن جامد 4 کیلو فامیلا', 1, 1, 1, 1, '29700', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/6b36af5da5e6aab1454ab8a23d79b162.jpg', 'uploads/6b36af5da5e6aab1454ab8a23d79b162.jpg', '2018-11-13 17:06:27', '2018-11-13 17:16:09', 17, 3, NULL),
(77, 'روغن جامد بدون پالم آفتاب', 1, 1, 1, 1, '29700', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/483ddfb4c840d30f189e831615862972.jpg', 'uploads/483ddfb4c840d30f189e831615862972.jpg', '2018-11-13 17:07:28', '2018-11-13 17:16:14', 17, 3, NULL),
(78, 'روغن جامد زیروترانس غنچه', 1, 1, 1, 1, '36600', '1', '0', 10, 'http://kharidchi.ir/admin/uploads/4ddd81415e7df0d5bcedc05262355182.jpg', 'uploads/4ddd81415e7df0d5bcedc05262355182.jpg', '2018-11-13 17:08:16', '2018-11-13 17:16:21', 17, 3, NULL),
(80, 'm4', 0, 0, 0, 0, '44', 'm4', '10', 10, 'http://kharidchi.ir/admin/uploads/19ad89bc3e3c9d7ef68b89523eff1987.jpeg', 'uploads/19ad89bc3e3c9d7ef68b89523eff1987.jpeg', '2018-11-21 11:39:59', '2018-11-21 11:39:59', 4, 1, NULL),
(81, 'm44', 0, 0, 0, 0, '44', 'm4', '10', 10, 'http://kharidchi.ir/admin/uploads/1bc29b36f623ba82aaf6724fd3b16718.jpeg', 'uploads/1bc29b36f623ba82aaf6724fd3b16718.jpeg', '2018-11-21 11:42:28', '2018-11-21 11:42:28', 4, 1, NULL),
(82, 'm4', 0, 0, 0, 0, '44', 'm4', '10', 10, 'http://kharidchi.ir/admin/uploads/19ad89bc3e3c9d7ef68b89523eff1987.jpg', 'uploads/19ad89bc3e3c9d7ef68b89523eff1987.jpg', '2018-11-21 11:43:19', '2018-11-21 11:43:19', 4, 1, NULL),
(83, 'm4', 0, 0, 0, 0, '44', 'm4', '10', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:46:10', '2018-11-21 11:46:10', 4, 1, NULL),
(84, 'm4', 0, 0, 0, 0, '44', 'm4', '10', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:46:44', '2018-11-21 11:46:44', 4, 1, NULL),
(85, 'm4', 0, 0, 0, 0, '44', 'm4', '10', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:47:36', '2018-11-21 11:47:36', 4, 1, NULL),
(86, 'sdv', 0, 0, 0, 0, 'sdv', 'dsv', 'sdvsdv', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:47:48', '2018-11-21 11:47:48', 4, 1, NULL),
(87, 'm4', 0, 0, 0, 0, '44', 'm4', '10', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:48:50', '2018-11-21 11:48:50', 4, 1, NULL),
(88, 'sdv', 0, 0, 0, 0, 'sdvd', 'sdvsdv', 'sdv', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:50:00', '2018-11-21 11:50:00', 4, 1, NULL),
(89, 'sdv', 0, 0, 0, 0, 'sdvsd', 'sdvs', 'sdvsdv', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:50:36', '2018-11-21 11:50:36', 4, 1, NULL),
(90, 'sdvs', 0, 0, 0, 0, 'sdvsdv', 'sdvsd', 'sdvsdv', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:51:48', '2018-11-21 11:51:48', 4, 1, NULL),
(91, 'm445', 0, 0, 0, 0, '22', '4', '34', 10, 'http://kharidchi.ir/admin/', '', '2018-11-21 11:52:16', '2018-11-21 11:52:16', 4, 1, NULL),
(92, 'ds', 0, 0, 0, 0, 'sdvdsv', 'sdvsdv', 'sdv', 10, 'http://kharidchi.ir/admin/uploads/3123059c1c816471780539f6b6b738dc.jpg', 'uploads/3123059c1c816471780539f6b6b738dc.jpg', '2018-11-21 11:52:44', '2018-11-21 11:52:44', 4, 1, NULL),
(93, 'sdvsdv', 0, 0, 0, 0, 'sdvsdv', 'sdv', 'sdv', 10, 'http://kharidchi.ir/admin/uploads/3123059c1c816471780539f6b6b738dc.jpg', 'uploads/3123059c1c816471780539f6b6b738dc.jpg', '2018-11-21 11:54:56', '2018-11-21 11:54:56', 4, 1, NULL),
(94, 'sdvsdv', 0, 0, 0, 0, 'sdvsdv', 'sdvdsv', 'sdvsdv', 10, 'http://kharidchi.ir/admin/uploads/3123059c1c816471780539f6b6b738dc.jpg', 'uploads/3123059c1c816471780539f6b6b738dc.jpg', '2018-11-21 11:56:21', '2018-11-21 11:56:21', 4, 1, NULL),
(95, 'dfbd', 0, 0, 0, 0, 'dfbdfb', 'dfbdfb', 'dfbdfb', 10, 'http://kharidchi.ir/admin/uploads/15de21c670ae7c3f6f3f1f37029303c9.jpg', 'uploads/15de21c670ae7c3f6f3f1f37029303c9.jpg', '2018-11-21 11:57:07', '2018-11-21 11:57:07', 4, 1, NULL),
(96, '44434', 1, 1, 1, 0, '3', '33', '888', 10, 'http://kharidchi.ir/admin/uploads/1bc29b36f623ba82aaf6724fd3b16718.jpg', 'uploads/1bc29b36f623ba82aaf6724fd3b16718.jpg', '2018-11-21 12:01:39', '2018-11-21 12:01:39', 4, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `id_category` int(11) NOT NULL,
  `id_fk` int(255) DEFAULT NULL,
  `name_category` varchar(50) NOT NULL,
  `image_url` varchar(1000) DEFAULT NULL,
  `web_image` varchar(1000) NOT NULL,
  `created_at_category` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_at_category` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_category` int(1) NOT NULL DEFAULT '1',
  `newest` int(1) NOT NULL DEFAULT '0',
  `first_page` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`id_category`, `id_fk`, `name_category`, `image_url`, `web_image`, `created_at_category`, `modify_at_category`, `status_category`, `newest`, `first_page`) VALUES
(1, NULL, 'ارگانیک', '', '', '2018-11-05 06:28:59', '0000-00-00 00:00:00', 0, 0, 1),
(2, NULL, 'میوه و سبزی', '', '', '2018-11-05 06:26:12', '0000-00-00 00:00:00', 0, 0, 1),
(3, NULL, 'آجیل', '', '', '2018-11-05 06:26:15', '0000-00-00 00:00:00', 0, 0, 1),
(4, 1, 'روغن', 'http://kharidchi.ir/admin/uploads/100-organic-logo-300x299.jpg', '', '2018-10-27 14:59:06', '0000-00-00 00:00:00', 0, 0, 1),
(5, 1, 'کره', '', '', '2018-10-27 14:59:02', '0000-00-00 00:00:00', 0, 0, 1),
(6, 1, 'ارده', '', '', '2018-10-27 14:58:59', '0000-00-00 00:00:00', 0, 0, 1),
(7, NULL, 'محصولات آشپزخانه', '', '', '2018-11-05 22:18:34', NULL, 0, 0, 1),
(8, 7, 'ظروف پذیرایی', '', '', '2018-11-05 06:30:00', NULL, 0, 0, 1),
(9, 7, 'ماگ و لیوان', '', '', '2018-11-06 12:17:08', NULL, 0, 0, 1),
(10, 7, 'فلاسک و کلمن', '', '', '2018-11-06 12:17:02', NULL, 0, 0, 1),
(11, 7, 'ظروف پخت و پز', '', '', '2018-11-06 12:16:58', NULL, 0, 0, 1),
(12, 7, 'ظروف نگهدارنده', '', '', '2018-11-06 12:16:54', NULL, 0, 0, 1),
(13, 7, 'لوازم برقی آشپزخانه', '', '', '2018-11-06 12:16:51', NULL, 0, 0, 1),
(14, 7, 'وسایل آشپزخانه', '', '', '2018-11-06 12:16:48', NULL, 0, 0, 1),
(15, NULL, 'مواد غذایی', '', '', '2018-11-13 16:19:34', '2018-11-13 15:05:18', 0, 0, 0),
(16, 15, 'روغن مایع', '', '', '2018-11-13 16:19:32', '2018-11-13 15:05:56', 0, 0, 1),
(17, 15, 'روغن جامد', '', '', '2018-11-13 16:20:18', '2018-11-13 16:18:14', 0, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `report` varchar(2000) COLLATE utf8_persian_ci NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`id`, `user_id`, `report`, `create_at`) VALUES
(1, 20, 'سلام خوبی', '2018-06-27 13:41:15'),
(2, 21, 'خرید که میکنم واسه تکمیل خرید و پرداخت هزینه باز نمیشه', '2018-06-28 05:39:30'),
(3, 7, 'مهندس امروز ۵شنبه هست اگه دو تا سفارشو زود بیاری ممنون میشم', '2018-06-28 08:00:03'),
(4, 33, 'سلام. امید جان یاشار هستم. مبارکه. به سلامتی. موفق باشی', '2018-06-30 12:09:58'),
(5, 42, 'با تشکر از اپ شما', '2018-10-14 15:28:32'),
(6, 7, 'سلام جناب مهندس امید عزیز،ساعت ۸ شب اومدم مغازه تشریف نداشتین،مشتاق دیدار', '2018-11-05 17:08:51');

-- --------------------------------------------------------

--
-- Table structure for table `SliderImage`
--

CREATE TABLE `SliderImage` (
  `id` int(11) NOT NULL,
  `path` varchar(500) COLLATE utf8_persian_ci NOT NULL,
  `value` varchar(20) COLLATE utf8_persian_ci NOT NULL,
  `status` int(1) NOT NULL,
  `status_search` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `SliderImage`
--

INSERT INTO `SliderImage` (`id`, `path`, `value`, `status`, `status_search`) VALUES
(1, 'http://kharidchi.ir/sliderImage/bannerSlider1.png', 'کنجد', 1, 1),
(2, 'http://kharidchi.ir/sliderImage/bannerSlider2.png', 'ارده', 1, 0),
(3, 'preview2.jpg', 'کره', 0, 0),
(4, 'preview.png', 'کنجد', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `sms_codes`
--

CREATE TABLE `sms_codes` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `code` varchar(6) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sms_codes`
--

INSERT INTO `sms_codes` (`id`, `user_id`, `code`, `status`, `created_at`, `update_at`) VALUES
(2, 2, '156218', 1, '2018-06-03 15:56:19', '0000-00-00 00:00:00'),
(3, 3, '584967', 1, '2018-06-05 06:17:01', '0000-00-00 00:00:00'),
(4, 4, '978550', 1, '2018-06-06 10:38:33', '2018-06-06 10:46:18'),
(5, 5, '578312', 0, '2018-06-06 10:43:31', '0000-00-00 00:00:00'),
(6, 5, '740049', 0, '2018-06-06 10:43:35', '0000-00-00 00:00:00'),
(7, 7, '199367', 1, '2018-06-07 15:57:00', '0000-00-00 00:00:00'),
(8, 8, '415043', 1, '2018-06-09 15:11:33', '0000-00-00 00:00:00'),
(9, 9, '237472', 1, '2018-06-11 14:13:49', '0000-00-00 00:00:00'),
(20, 20, '146105', 1, '2018-06-27 13:36:55', '0000-00-00 00:00:00'),
(21, 21, '763740', 1, '2018-06-28 05:35:13', '2018-10-20 12:40:53'),
(22, 22, '172386', 1, '2018-06-28 06:19:42', '0000-00-00 00:00:00'),
(23, 23, '541356', 1, '2018-06-28 06:45:43', '0000-00-00 00:00:00'),
(24, 24, '457802', 1, '2018-06-28 07:07:37', '0000-00-00 00:00:00'),
(25, 25, '636491', 1, '2018-06-28 08:38:15', '0000-00-00 00:00:00'),
(26, 26, '880495', 1, '2018-06-28 08:42:25', '0000-00-00 00:00:00'),
(27, 27, '636985', 1, '2018-06-28 20:21:09', '0000-00-00 00:00:00'),
(30, 30, '676163', 1, '2018-06-30 08:14:39', '0000-00-00 00:00:00'),
(31, 31, '792893', 1, '2018-06-30 10:48:31', '0000-00-00 00:00:00'),
(32, 32, '134388', 1, '2018-06-30 11:00:37', '0000-00-00 00:00:00'),
(33, 33, '195434', 1, '2018-06-30 12:05:46', '0000-00-00 00:00:00'),
(34, 34, '377004', 1, '2018-07-01 15:54:16', '0000-00-00 00:00:00'),
(36, 36, '620897', 1, '2018-07-05 16:34:23', '0000-00-00 00:00:00'),
(37, 37, '164346', 1, '2018-07-06 13:43:30', '0000-00-00 00:00:00'),
(38, 38, '775668', 1, '2018-07-13 08:52:16', '0000-00-00 00:00:00'),
(40, 40, '218586', 1, '2018-10-07 18:14:38', '2018-11-01 09:14:36'),
(41, 41, '439121', 1, '2018-10-13 08:53:40', '0000-00-00 00:00:00'),
(42, 42, '465701', 1, '2018-10-14 15:20:49', '0000-00-00 00:00:00'),
(43, 43, '760125', 1, '2018-10-27 12:52:31', '0000-00-00 00:00:00'),
(44, 44, '443171', 1, '2018-10-27 20:21:37', '0000-00-00 00:00:00'),
(45, 45, '959706', 1, '2018-10-28 15:30:25', '0000-00-00 00:00:00'),
(46, 46, '898649', 1, '2018-10-31 10:05:12', '0000-00-00 00:00:00'),
(47, 47, '857225', 1, '2018-11-05 22:41:45', '0000-00-00 00:00:00'),
(48, 48, '257443', 1, '2018-11-09 09:46:15', '0000-00-00 00:00:00'),
(49, 49, '352471', 1, '2018-11-10 13:31:00', '0000-00-00 00:00:00'),
(50, 50, '570198', 1, '2018-11-24 07:53:15', '0000-00-00 00:00:00'),
(51, 51, '358022', 1, '2018-11-25 08:24:49', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE `state` (
  `id` int(11) NOT NULL,
  `state_name` varchar(15) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`id`, `state_name`) VALUES
(1, 'آذربایجان غربی');

-- --------------------------------------------------------

--
-- Table structure for table `town`
--

CREATE TABLE `town` (
  `id` int(11) NOT NULL,
  `town_name` varchar(15) COLLATE utf8_persian_ci NOT NULL,
  `state_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `town`
--

INSERT INTO `town` (`id`, `town_name`, `state_id`) VALUES
(1, 'خوی', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `family` varchar(250) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `is_active` int(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pass` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `family`, `mobile`, `status`, `is_active`, `created_at`, `update_at`, `pass`) VALUES
(2, 'omid', 'rayaneh', '09392027771', 1, 1, '2018-06-03 15:56:19', '0000-00-00 00:00:00', '862c8f1eae79fcec63cbe5140281490a'),
(3, ' افسان', 'صادقی', '09143636595', 1, 1, '2018-06-05 06:17:01', '0000-00-00 00:00:00', '862c8f1eae79fcec63cbe5140281490a'),
(4, 'hajie', 'haghayegi', '09144612713', 1, 1, '2018-06-06 10:38:33', '2018-06-06 10:46:18', '59738666561b6aa0f370d8fa2b8b3271'),
(5, 'غاه', 'اابل', '09855453254', 0, 1, '2018-06-06 10:43:31', '0000-00-00 00:00:00', '8053ec1f75b599e107014995c1452a75'),
(6, 'غاه', 'اابل', '09855453254', 0, 1, '2018-06-06 10:43:35', '0000-00-00 00:00:00', '8053ec1f75b599e107014995c1452a75'),
(7, 'یوسف', 'لقایی', '09143610902', 1, 1, '2018-06-07 15:57:00', '0000-00-00 00:00:00', '1328b255aa980a198fa152e4f70fe1ea'),
(8, 'tohid', 'moradi', '09382629174', 1, 1, '2018-06-09 15:11:33', '0000-00-00 00:00:00', '0fd0dd4729c1eedfed674e9e8887cc2a'),
(9, 'asd', 'asdsad', '09141640249', 1, 1, '2018-06-11 14:13:49', '0000-00-00 00:00:00', '862c8f1eae79fcec63cbe5140281490a'),
(20, 'esmaeil', 'galavani', '09147904600', 1, 1, '2018-06-27 13:36:55', '0000-00-00 00:00:00', '0f64de39f50f00b1f243f994cb572d75'),
(21, 'امین', 'صادقی', '09176282098', 1, 1, '2018-06-28 05:35:13', '2018-10-20 12:40:53', 'faa0dd0412f65c0ac1e505d0cfd0c593'),
(22, 'داریوش ', 'پاشاپور', '09143635019', 1, 1, '2018-06-28 06:19:42', '0000-00-00 00:00:00', 'cc7f92b9c8573a1b03a86084962b18c3'),
(23, 'Amir', 'BroukiMilan', '09144630823', 1, 1, '2018-06-28 06:45:43', '0000-00-00 00:00:00', 'cbe1254ce6c5cc988aa24df6d356226e'),
(24, 'امین', 'محمدزاده', '09358084194', 1, 1, '2018-06-28 07:07:37', '0000-00-00 00:00:00', '7c40b077ea89280d7628767505508fab'),
(25, 'مهیار', 'طلوعی', '09357805893', 1, 1, '2018-06-28 08:38:15', '0000-00-00 00:00:00', '0fd0dd4729c1eedfed674e9e8887cc2a'),
(26, 'پرویز', 'علیلو', '09147128326', 1, 1, '2018-06-28 08:42:25', '0000-00-00 00:00:00', '19651651147413d0c9ba8f5cb727e4cc'),
(27, 'اکرم', 'صادقی', '09335881689', 1, 1, '2018-06-28 20:21:09', '0000-00-00 00:00:00', '632b78645d56aed69cd062e8e41fd118'),
(30, 'Tohid', 'Gholipoor', '09147209568', 1, 1, '2018-06-30 08:14:39', '0000-00-00 00:00:00', 'e9b24f9498c80a86d245223f9f0fcc7b'),
(31, 'رضا', 'فولادپنجه', '09398652211', 1, 1, '2018-06-30 10:48:31', '0000-00-00 00:00:00', '9b4f3fb219e2ca78ff8457b998caa689'),
(32, 'بهزاد', 'کریمی', '09149604591', 1, 1, '2018-06-30 11:00:37', '0000-00-00 00:00:00', 'cb620f7db5001e2394f6897ff4d6a891'),
(33, 'یاشار', 'گلدوست', '09374605873', 1, 1, '2018-06-30 12:05:46', '0000-00-00 00:00:00', '0fd0dd4729c1eedfed674e9e8887cc2a'),
(34, 'مجتبی', 'قربانعلی زاده', '09360338506', 1, 1, '2018-07-01 15:54:16', '0000-00-00 00:00:00', '0fd0dd4729c1eedfed674e9e8887cc2a'),
(36, 'محرم', 'نوری', '09302209399', 1, 1, '2018-07-05 16:34:23', '0000-00-00 00:00:00', 'af249a683cb789cdc282b0b3b7b7fc9c'),
(37, 'محمد', 'باقر', '09101606781', 1, 1, '2018-07-06 13:43:30', '0000-00-00 00:00:00', '0bdd76cbbbb14339ee1e2d42edb3aca7'),
(38, 'مهدی', 'کاظمی', '09143205236', 1, 1, '2018-07-13 08:52:16', '0000-00-00 00:00:00', '50fadb37a1c952bc52ced5e465d0f4a0'),
(40, 'omid', 'sadeghi', '09143612440', 1, 1, '2018-10-07 18:14:38', '2018-11-01 09:14:36', '0fd0dd4729c1eedfed674e9e8887cc2a'),
(41, 'حامد', 'دولتی', '09141637664', 1, 1, '2018-10-13 08:53:40', '2018-10-13 08:53:40', '0fd0dd4729c1eedfed674e9e8887cc2a'),
(42, 'محمد جواد', 'کیوان', '09143616571', 1, 1, '2018-10-14 15:20:49', '2018-10-14 15:20:49', 'fd0a31faf4374c71e9ec1ef0b70d849b'),
(43, 'fcjccj', 'fuuduxcj', '09025436046', 1, 1, '2018-10-27 12:52:31', '2018-10-27 12:52:31', '0fd0dd4729c1eedfed674e9e8887cc2a'),
(44, 'علی', 'علاقه بند', '09136884719', 1, 1, '2018-10-27 20:21:37', '2018-10-27 20:21:37', 'b5abfb174429d25a82c5fbd2122ff4e0'),
(45, 'تاریخ عضویت آذر', 'دانلود آهنگ جدید', '09384837410', 1, 1, '2018-10-28 15:30:25', '2018-10-28 15:30:25', 'ff7cf2397060062baea16ce4441c8195'),
(46, 'سعید ', 'قرقره چی ', '09122060694', 1, 1, '2018-10-31 10:05:12', '2018-10-31 10:05:12', '5770c77ce7d090e88586eac84f1b626c'),
(47, 'جواد', 'خیاطی', '09147126389', 1, 1, '2018-11-05 22:41:45', '2018-11-05 22:41:45', '54f8ae97bf0c90f973b35581f48a6167'),
(48, 'matin', 'yousefi', '09397789976', 1, 1, '2018-11-09 09:46:15', '2018-11-09 09:46:15', '9d2ba81fe94e14ed4a0672cc27a0b735'),
(49, 'توحید', 'حاتملو', '09144616013', 1, 1, '2018-11-10 13:31:00', '2018-11-10 13:31:00', '4eb60aad2f87636fe99a5e0504e14e4c'),
(50, 'test', 'hastim', '09339432358', 1, 1, '2018-11-24 07:53:15', '2018-11-24 07:53:15', '0fd0dd4729c1eedfed674e9e8887cc2a'),
(51, 'رضا', 'سلیم زاده', '09149773034', 1, 1, '2018-11-25 08:24:49', '2018-11-25 08:24:49', '73cd70067c77b2eeac2369d83fc3e7b1');

-- --------------------------------------------------------

--
-- Table structure for table `user_address`
--

CREATE TABLE `user_address` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  `full_address` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `phone` varchar(11) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `user_address`
--

INSERT INTO `user_address` (`id`, `user_id`, `town_id`, `full_address`, `phone`) VALUES
(1, 2, 1, 'خیابان امام', '04436225242'),
(2, 3, 1, 'کوی شهدا کوچه چهارم', '04436440685'),
(4, 4, 1, 'فلکه مادر، کوی شهدا، کوچه افتخاری 2، پلاک 22', '04436442728'),
(5, 4, 1, 'فلکه مادر، کوی شهدا، کوچه افتخاری 2، پلاک 22', '04436442728'),
(6, 7, 1, 'دارایی', '09143610902'),
(7, 9, 1, 'مطهری', '04436440685'),
(9, 20, 1, 'خیابان امام روبروی مسجد ملا احمد', '04436260056'),
(10, 21, 1, 'کوی شهدا کوچه عزیز حسن نژاد', '09176282098'),
(12, 30, 1, 'میدان بسیج.خیابان منتظری.سهدیه اول پلاک ۱۱', '09147209568'),
(13, 8, 1, 'اددد', '04436240434'),
(14, 34, 1, 'مغاره خودت', '0123456789'),
(15, 36, 1, 'khoy   ', '09302209399'),
(16, 38, 1, 'مراغه', '07894555455'),
(17, 40, 1, 'خیابان امام', '04436225242'),
(18, 41, 1, 'امور مالیاتی خوی', '09141637664'),
(19, 42, 1, 'خیابان امام کوچه شهید سلطانزاده پلاک ۴۲', '04436224592'),
(20, 43, 1, 'خلهبهلهل', '09025436046'),
(21, 45, 1, 'دانلود آهنگ جدید و', '09384837410'),
(22, 49, 1, '۷خوی خ انقلاب موبایل ترک سل', '36460370'),
(23, 50, 1, 'تست هستیم ما در ', ''),
(24, 51, 1, 'میدان فهمیده', '09149773034');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cost`
--
ALTER TABLE `cost`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `details`
--
ALTER TABLE `details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_product` (`id_products`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `invoice_items`
--
ALTER TABLE `invoice_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `invoice_id` (`invoice_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_product`),
  ADD KEY `product_category_id` (`product_category_id`),
  ADD KEY `owner_id_` (`owner_id_`) USING BTREE,
  ADD KEY `owner_id` (`owner_id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id_category`),
  ADD KEY `id_fk` (`id_fk`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`) USING BTREE;

--
-- Indexes for table `SliderImage`
--
ALTER TABLE `SliderImage`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_codes`
--
ALTER TABLE `sms_codes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`) USING BTREE;

--
-- Indexes for table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `town`
--
ALTER TABLE `town`
  ADD PRIMARY KEY (`id`),
  ADD KEY `state_id` (`state_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_address`
--
ALTER TABLE `user_address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `town_id` (`town_id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `cost`
--
ALTER TABLE `cost`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `details`
--
ALTER TABLE `details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `invoice_items`
--
ALTER TABLE `invoice_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `owner`
--
ALTER TABLE `owner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `SliderImage`
--
ALTER TABLE `SliderImage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sms_codes`
--
ALTER TABLE `sms_codes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `town`
--
ALTER TABLE `town`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `user_address`
--
ALTER TABLE `user_address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `details`
--
ALTER TABLE `details`
  ADD CONSTRAINT `details_ibfk_1` FOREIGN KEY (`id_products`) REFERENCES `product` (`id_product`) ON DELETE CASCADE;

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `invoice_items`
--
ALTER TABLE `invoice_items`
  ADD CONSTRAINT `invoice_items_ibfk_1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `invoice_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id_product`) ON DELETE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id_category`) ON DELETE CASCADE,
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`owner_id_`) REFERENCES `owner` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `product_ibfk_3` FOREIGN KEY (`owner_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `product_category`
--
ALTER TABLE `product_category`
  ADD CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`id_fk`) REFERENCES `product_category` (`id_category`) ON DELETE CASCADE;

--
-- Constraints for table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `sms_codes`
--
ALTER TABLE `sms_codes`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `saddress_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sms_codes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `town`
--
ALTER TABLE `town`
  ADD CONSTRAINT `town_ibfk_1` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_address`
--
ALTER TABLE `user_address`
  ADD CONSTRAINT `user_address_ibfk_1` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_address_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
