-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2023 at 04:50 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tallyswift`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ProductID` varchar(5) NOT NULL,
  `CategoryID` varchar(5) NOT NULL,
  `ProductName` varchar(255) NOT NULL,
  `Price` int(11) NOT NULL,
  `Stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductID`, `CategoryID`, `ProductName`, `Price`, `Stock`) VALUES
('PR001', 'KG001', 'Teh Gelas', 3000, 150),
('PR002', 'KG001', 'Yakult', 6000, 80),
('PR003', 'KG002', 'Indomie', 5000, 200),
('PR004', 'KG003', 'Beras', 12000, 90),
('PR005', 'KG004', 'Paracetamol', 5000, 50),
('PR006', 'KG002', 'Kopi Sachet', 4000, 120),
('PR007', 'KG001', 'Aqua', 2000, 170),
('PR008', 'KG003', 'Gula Pasir', 8000, 100),
('PR009', 'KG004', 'Vitamin C', 15000, 30),
('PR010', 'KG002', 'Mie Goreng', 7000, 110),
('PR011', 'KG001', 'Teh Pucuk', 3500, 130),
('PR012', 'KG002', 'Chitato', 8000, 95),
('PR013', 'KG001', 'Susu Ultra Milk', 7500, 70),
('PR014', 'KG003', 'Minyak Goreng', 10000, 160),
('PR015', 'KG004', 'Obh Combi', 10000, 25),
('PR016', 'KG002', 'Keripik Singkong', 9000, 180),
('PR017', 'KG001', 'Coca Cola', 5000, 60),
('PR018', 'KG002', 'Roti Tawar', 7000, 140),
('PR019', 'KG001', 'Susu Kental Manis', 8500, 75),
('PR020', 'KG003', 'Lada Hitam', 12000, 50),
('PR021', 'KG004', 'Antimo', 6000, 35),
('PR022', 'KG001', 'Jus Jeruk', 6500, 110),
('PR023', 'KG003', 'Kecap ABC', 9000, 80),
('PR024', 'KG002', 'Chocolatos', 12000, 45),
('PR025', 'KG001', 'Air Mineral Botol', 3000, 200),
('PR026', 'KG004', 'Betadine', 8000, 30),
('PR027', 'KG001', 'Kopi ABC', 5500, 90),
('PR028', 'KG002', 'Kacang Tanah', 6000, 130),
('PR029', 'KG003', 'Bawang Merah', 7500, 75),
('PR030', 'KG004', 'Amoxilin', 10000, 20),
('PR031', 'KG001', 'Sprite', 4500, 120),
('PR032', 'KG002', 'Sosis', 11000, 70),
('PR033', 'KG003', 'Garam', 5000, 170),
('PR034', 'KG004', 'Panadol', 6000, 40),
('PR035', 'KG001', 'Fanta', 4000, 140),
('PR036', 'KG002', 'Ayam Kaleng', 12000, 60),
('PR037', 'KG003', 'Bumbu Masak ABC', 10000, 50),
('PR038', 'KG004', 'Diapet', 15000, 25),
('PR039', 'KG001', 'Pocari Sweat', 7000, 180),
('PR040', 'KG002', 'Biskuit Roma', 5000, 130),
('PR041', 'KG003', 'Telur', 12000, 90),
('PR042', 'KG004', 'Betnovate', 18000, 15),
('PR043', 'KG001', 'Lipton Tea', 3500, 160),
('PR044', 'KG002', 'Bolu Kukus', 10000, 70),
('PR045', 'KG003', 'Santan Kara', 8000, 50),
('PR046', 'KG004', 'Strepsils', 6000, 30),
('PR047', 'KG001', 'Ultramilk', 9000, 110),
('PR048', 'KG002', 'Cokelat Silverqueen', 15000, 65),
('PR049', 'KG003', 'Sambal ABC', 7000, 120),
('PR050', 'KG004', 'Vitacimin', 12000, 20);

-- --------------------------------------------------------

--
-- Table structure for table `productcategory`
--

CREATE TABLE `productcategory` (
  `CategoryID` varchar(5) NOT NULL,
  `Category` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productcategory`
--

INSERT INTO `productcategory` (`CategoryID`, `Category`) VALUES
('KG001', 'Minuman'),
('KG002', 'Makanan'),
('KG003', 'Bahan Masakan'),
('KG004', 'Obat');

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetail`
--

CREATE TABLE `transactiondetail` (
  `TransactionID` varchar(5) NOT NULL,
  `ProductID` varchar(5) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `transactionheader`
--

CREATE TABLE `transactionheader` (
  `TransactionID` varchar(5) NOT NULL,
  `UserID` varchar(10) NOT NULL,
  `TransactionDate` date NOT NULL,
  `PaymentMethod` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Password`) VALUES
('admin', 'admin123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`);

--
-- Indexes for table `productcategory`
--
ALTER TABLE `productcategory`
  ADD PRIMARY KEY (`CategoryID`);

--
-- Indexes for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD PRIMARY KEY (`TransactionID`,`ProductID`);

--
-- Indexes for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD PRIMARY KEY (`TransactionID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
