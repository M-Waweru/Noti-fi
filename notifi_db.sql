
-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 18, 2018 at 10:03 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `notifi_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `Admin No` int(5) NOT NULL,
  `Admin Name` varchar(30) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Admin Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`Admin No`, `Admin Name`, `Description`, `Admin Password`) VALUES
(0, 'Superuser', 'The ultimate admin', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `Notification No` varchar(50) NOT NULL,
  `Notification Subject` varchar(30) NOT NULL,
  `Notification Content` varchar(200) NOT NULL,
  `Admin No` int(5) NOT NULL,
  `Notification Type No` int(5) NOT NULL,
  `Date created` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`Notification No`, `Notification Subject`, `Notification Content`, `Admin No`, `Notification Type No`, `Date created`) VALUES
('594b8db3-0289-4523-a969-659719378225', 'Hello there', 'General Kenobi', 0, 0, '2018-09-13T07:40:49.983');

-- --------------------------------------------------------

--
-- Table structure for table `notification_type`
--

CREATE TABLE `notification_type` (
  `Type No` int(5) NOT NULL,
  `Type Name` varchar(20) NOT NULL,
  `Description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification_type`
--

INSERT INTO `notification_type` (`Type No`, `Type Name`, `Description`) VALUES
(0, 'General', 'This is general info');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `Schedule No` varchar(50) NOT NULL,
  `Notification No` varchar(50) NOT NULL,
  `Time` time NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`Admin No`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`Notification No`),
  ADD KEY `Admin No` (`Admin No`),
  ADD KEY `Notification Type No` (`Notification Type No`);

--
-- Indexes for table `notification_type`
--
ALTER TABLE `notification_type`
  ADD PRIMARY KEY (`Type No`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`Schedule No`),
  ADD KEY `Notification No` (`Notification No`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`Admin No`) REFERENCES `admins` (`Admin No`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`Notification Type No`) REFERENCES `notification_type` (`Type No`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`Notification No`) REFERENCES `notifications` (`Notification No`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
=======
-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2018 at 12:37 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `notifi_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `Admin No` int(5) NOT NULL,
  `Admin Name` varchar(30) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Admin Password` varchar(100) NOT NULL,
  `Salt` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`Admin No`, `Admin Name`, `Description`, `Admin Password`, `Salt`) VALUES
(1, 'Superuser', 'The ultimate admin', '[B@67117f44', '[B@5d3411d');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `Notification No` int(50) NOT NULL,
  `Notification Subject` varchar(100) NOT NULL,
  `Notification Content` varchar(600) NOT NULL,
  `Admin No` int(5) NOT NULL,
  `Notification Type No` int(5) NOT NULL,
  `Date created` varchar(30) NOT NULL,
  `Image dir` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`Notification No`, `Notification Subject`, `Notification Content`, `Admin No`, `Notification Type No`, `Date created`, `Image dir`) VALUES
(598, 'Hello there', 'Hello there, it\'s Mathenge with an announcement to make.', 1, 1, '2018-10-25T12:46:10.809', 'C:\\Users\\Mathenge\\Documents\\Projects\\WebAdminModule\\target\\WebAdminModule-1.0-SNAPSHOT\\notimages\\Black-Roses_2048x.jpg'),
(599, 'Notice', 'According to ...', 1, 1, '2018-10-25T13:06:15.023', 'C:\\Users\\Mathenge\\Documents\\Projects\\WebAdminModule\\target\\WebAdminModule-1.0-SNAPSHOT\\notimages\\Black-Roses_2048x.jpg'),
(600, 'Notice', 'According to ...', 1, 1, '2018-10-25T13:12:25.333', 'C:\\Users\\Mathenge\\Documents\\Projects\\WebAdminModule\\target\\WebAdminModule-1.0-SNAPSHOT\\notimages\\Black-Roses_2048x.jpg'),
(601, 'Notice', 'According to ...', 1, 1, '2018-10-25T13:14:21.644', 'C:\\Users\\Mathenge\\Documents\\Projects\\WebAdminModule\\target\\WebAdminModule-1.0-SNAPSHOT\\notimages\\Black-Roses_2048x.jpg'),
(602, 'Notice', 'According to ...', 1, 1, '2018-10-25T13:15:18.126', 'C:\\Users\\Mathenge\\Documents\\Projects\\WebAdminModule\\target\\WebAdminModule-1.0-SNAPSHOT\\notimages\\Black-Roses_2048x.jpg'),
(603, 'dsd', 'asdasd', 1, 1, '2018-10-25T14:12:07.107', 'C:\\Users\\Mathenge\\Documents\\Projects\\WebAdminModule\\target\\WebAdminModule-1.0-SNAPSHOT\\notimages\\IMG_1619.jpg'),
(604, 'dsd', 'asdasd', 1, 1, '2018-10-25T14:13:01.147', 'C:\\Users\\Mathenge\\Documents\\Projects\\WebAdminModule\\target\\WebAdminModule-1.0-SNAPSHOT\\notimages\\IMG_1619.jpg'),
(605, 'dsd', 'asdasd', 1, 1, '2018-10-25T14:13:42.220', 'C:\\Users\\Mathenge\\Documents\\Projects\\WebAdminModule\\target\\WebAdminModule-1.0-SNAPSHOT\\notimages\\IMG_1619.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `notification_type`
--

CREATE TABLE `notification_type` (
  `Type No` int(5) NOT NULL,
  `Type Name` varchar(20) NOT NULL,
  `Description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification_type`
--

INSERT INTO `notification_type` (`Type No`, `Type Name`, `Description`) VALUES
(1, 'General', 'This is general info');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `Schedule No` int(50) NOT NULL,
  `Notification No` int(50) NOT NULL,
  `Time` varchar(40) DEFAULT NULL,
  `Date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`Schedule No`, `Notification No`, `Time`, `Date`) VALUES
(1, 602, NULL, 'Oct 26, 2018'),
(2, 603, NULL, 'Oct 29, 2018'),
(3, 604, NULL, 'Oct 29, 2018'),
(4, 605, NULL, 'Oct 29, 2018');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`Admin No`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`Notification No`),
  ADD KEY `Admin No` (`Admin No`),
  ADD KEY `Notification Type No` (`Notification Type No`);

--
-- Indexes for table `notification_type`
--
ALTER TABLE `notification_type`
  ADD PRIMARY KEY (`Type No`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`Schedule No`),
  ADD KEY `Notification No` (`Notification No`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `Admin No` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `Notification No` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=606;

--
-- AUTO_INCREMENT for table `notification_type`
--
ALTER TABLE `notification_type`
  MODIFY `Type No` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `Schedule No` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`Admin No`) REFERENCES `admins` (`Admin No`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`Notification Type No`) REFERENCES `notification_type` (`Type No`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`Notification No`) REFERENCES `notifications` (`Notification No`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
>>>>>>> f0f7248ed27a3b120167b8eb9e12a4326564889d
