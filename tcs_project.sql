-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 20, 2016 at 04:14 PM
-- Server version: 5.7.13-0ubuntu0.16.04.2
-- PHP Version: 7.0.8-0ubuntu0.16.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tcs_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `Hierarchy`
--

CREATE TABLE `Hierarchy` (
  `Position` varchar(40) NOT NULL,
  `Reports To Position` varchar(40) NOT NULL,
  `Name of Incumbent` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Hierarchy`
--

INSERT INTO `Hierarchy` (`Position`, `Reports To Position`, `Name of Incumbent`) VALUES
('CEO', 'Chairman', 'Roger'),
('Chairman', 'Chairman', 'John'),
('Director 1', 'Vice President 1', 'James'),
('Director 2', 'Vice President 2', 'Charles'),
('PS', 'Chairman', 'Krishnan'),
('Staff 1', 'Director 1', 'Joe'),
('Vice President 2', 'CEO', 'Rajan'),
('Vice President 1', 'CEO', 'phil');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `Id` int(11) NOT NULL,
  `Name` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`Id`, `Name`, `Password`) VALUES
(1, 'Roger', 'Roger'),
(2, 'John', 'John'),
(3, 'James', 'James'),
(4, 'Charles', 'Charles'),
(5, 'Krishnan', 'Krishnan'),
(6, 'Joe', 'Joe'),
(7, 'Rajan', 'Rajan'),
(8, 'phil', 'phil');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Hierarchy`
--
ALTER TABLE `Hierarchy`
  ADD UNIQUE KEY `Position` (`Position`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
