-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 17, 2022 at 06:51 AM
-- Server version: 5.7.24
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `accounting_db01`
--

-- --------------------------------------------------------

--
-- Table structure for table `consumption_category`
--

CREATE TABLE `consumption_category` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `consumption_category`
--

INSERT INTO `consumption_category` (`id`, `name`) VALUES
(1, 'food'),
(2, 'bill'),
(3, 'transportation'),
(4, 'clothes'),
(5, 'education'),
(6, 'investment');

-- --------------------------------------------------------

--
-- Table structure for table `single_consumption_record`
--

CREATE TABLE `single_consumption_record` (
  `id` int(10) UNSIGNED NOT NULL,
  `amount_of_money` int(11) NOT NULL,
  `date` date NOT NULL,
  `category_id` tinyint(4) NOT NULL DEFAULT '30',
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `single_consumption_record`
--

INSERT INTO `single_consumption_record` (`id`, `amount_of_money`, `date`, `category_id`, `description`) VALUES
(1, 54305, '2022-10-26', 6, '更新測試'),
(4, 120, '2022-11-12', 2, 'fkckirhcoerh'),
(6, 180, '2022-11-03', 1, NULL),
(7, 9876, '2022-10-04', 5, 'test update'),
(8, 100, '2022-11-04', 1, NULL),
(10, 100, '2022-11-05', 1, ''),
(11, 150, '2022-11-10', 4, 'test'),
(12, 200, '2022-11-08', 6, 'test2'),
(14, 90, '2022-11-02', 3, 'bus'),
(15, 543, '2022-11-22', 2, 'test update'),
(17, 1000, '2022-10-10', 5, 'test3'),
(18, 1300, '2022-10-17', 5, '中文'),
(20, 900, '2022-10-27', 3, 'update33445'),
(22, 9999, '2022-10-28', 6, 'aaaaaaaaaaaaaaaaaa'),
(24, 4000, '2022-11-14', 4, '測試新增'),
(25, 299, '2022-11-15', 1, '測試搜尋今日'),
(26, 9981, '2021-10-12', 3, 'test search year'),
(27, 43423, '2021-09-08', 4, 'testtt'),
(28, 300, '2021-10-21', 2, 'test23456'),
(29, 1000, '2022-11-16', 2, '搜尋時新增');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consumption_category`
--
ALTER TABLE `consumption_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `single_consumption_record`
--
ALTER TABLE `single_consumption_record`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consumption_category`
--
ALTER TABLE `consumption_category`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `single_consumption_record`
--
ALTER TABLE `single_consumption_record`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
