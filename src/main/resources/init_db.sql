CREATE TABLE IF NOT EXISTS `pics` (
                        `Id` bigint NOT NULL AUTO_INCREMENT,
                        `Name` varchar(255) NOT NULL,
                        `MimeType` varchar(255) NOT NULL,
                        `Size` bigint NOT NULL,
                        `digitalSign` varchar NOT NULL,
                        `created_at` DATETIME NOT NULL,
                        `updated_at` DATETIME,
                        `is_deleted` BOOLEAN NOT NULL DEFAULT false,
                        PRIMARY KEY (`Id`)
);

