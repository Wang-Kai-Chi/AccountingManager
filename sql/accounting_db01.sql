-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 11, 2022 at 08:25 AM
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
(1, 100, '2022-11-01', 1, NULL),
(2, 150, '2022-11-01', 1, NULL),
(3, 80, '2022-11-02', 2, NULL),
(4, 120, '2022-11-02', 2, NULL),
(5, 180, '2022-11-02', 3, NULL),
(6, 180, '2022-11-03', 1, NULL),
(7, 100, '2022-11-04', 1, NULL),
(8, 100, '2022-11-04', 1, NULL),
(9, 100, '2022-11-05', 1, 'dinner'),
(10, 100, '2022-11-05', 1, '');

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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
