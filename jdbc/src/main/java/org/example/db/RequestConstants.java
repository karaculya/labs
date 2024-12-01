package org.example.db;

public interface RequestConstants {
    String DROP_TABLES = "DROP TABLE IF EXISTS compositions CASCADE;\n" +
            "DROP TABLE IF EXISTS albums CASCADE;\n" +
            "DROP TABLE IF EXISTS artists CASCADE;\n" +
            "DROP SEQUENCE IF EXISTS compositions_id_seq;\n" +
            "DROP SEQUENCE IF EXISTS albums_id_seq;\n" +
            "DROP SEQUENCE IF EXISTS artists_id_seq;";

    String CREATE_TABLE_ARTISTS = "CREATE TABLE artists (\n" +
            "                    id INT GENERATED ALWAYS AS IDENTITY,\n" +
            "                    name VARCHAR(64) NOT NULL,\n" +
            "                    PRIMARY KEY(id)\n" +
            ");";

    String CREATE_TABLE_ALBUMS = "CREATE TABLE albums (\n" +
            "                    id INT GENERATED ALWAYS AS IDENTITY,\n" +
            "                    name VARCHAR(64) NOT NULL,\n" +
            "                    genre VARCHAR(32) NOT NULL,\n" +
            "                    artist_id INT,\n" +
            "                    PRIMARY KEY(id),\n" +
            "                    FOREIGN KEY (artist_id)\n" +
            "                        REFERENCES artists (id)\n" +
            "                            ON DELETE CASCADE\n" +
            "                            ON UPDATE CASCADE\n" +
            ");";

    String CREATE_TABLE_COMPOSITIONS = "CREATE TABLE compositions (\n" +
            "                    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n" +
            "                    name VARCHAR(64) NOT NULL,\n" +
            "                    duration TIME NOT NULL,\n" +
            "                    album_id INT,\n" +
            "                    FOREIGN KEY (album_id)\n" +
            "                        REFERENCES albums (id)\n" +
            "                            ON DELETE CASCADE\n" +
            "                            ON UPDATE CASCADE\n" +
            ");";

    String INSERT_INTO_ARTISTS = "INSERT INTO artists (name) VALUES ('My Chemical Romance');\n" +
            "INSERT INTO artists (name) VALUES ('Nirvana');\n" +
            "INSERT INTO artists (name) VALUES ('Bring Me The Horizon');\n" +
            "INSERT INTO artists (name) VALUES ('The Weekend');\n" +
            "INSERT INTO artists (name) VALUES ('Placebo');";

    String INSERT_INTO_ALBUMS = "INSERT INTO albums (name, genre, artist_id) VALUES ('Three Cheers for Sweet Revenge', 'Rock', 1);\n" +
            "INSERT INTO albums (name, genre, artist_id) VALUES ('Nevermind', 'Grunge', 2);\n" +
            "INSERT INTO albums (name, genre, artist_id) VALUES ('2004 - 2013', 'Metal', 3);\n" +
            "INSERT INTO albums (name, genre, artist_id) VALUES ('After Hours', 'R&B', 4);\n" +
            "INSERT INTO albums (name, genre, artist_id) VALUES ('Without You I am Nothing', 'Alternative Rock', 5);";

    String INSERT_INTO_COMPOSITIONS = "INSERT INTO compositions (name, duration, album_id) VALUES ('Helena', '00:03:24', 1);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('I am Not Okay (I Promise)', '00:03:06', 1);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Thank You for the Venom', '00:03:41', 1);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('I Never Told You What I Do for a Living', '00:03:52', 1);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Cemetery Drive', '00:03:08', 1);\n" +
            "\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Smells Like Teen Spirit', '00:05:01', 2);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('In Bloom', '00:04:15', 2);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Come As You Are', '00:03:39', 2);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Breed', '00:03:03', 2);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Lithium', '00:04:17', 2);\n" +
            "\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Sleepwalking', '00:03:51', 3);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Can You Feel My Heart', '00:03:47', 3);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('The Comedown', '00:04:09', 3);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Blessed With A Curse', '00:05:08', 3);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('It Never Ends', '00:04:34', 3);\n" +
            "\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Blinding Lights', '00:03:20', 4);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Save Your Tears', '00:03:35', 4);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('In Your Eyes', '00:03:57', 4);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Heartless', '00:03:18', 4);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('After Hours', '00:06:01', 4);\n" +
            "\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Pure Morning', '00:04:14', 5);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Brick Shithouse', '00:03:18', 5);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('You Don not Care About Us', '00:03:57', 5);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Ask for Answers', '00:05:19', 5);\n" +
            "INSERT INTO compositions (name, duration, album_id) VALUES ('Without You I am Nothing', '00:04:10', 5);";

    String GROUP_BY_HAVING = "SELECT alb.name as album, comp.name as composition, comp.duration\n" +
            "FROM albums alb\n" +
            "JOIN compositions comp ON alb.id = comp.album_id\n" +
            "WHERE comp.duration >= '00:05:00'\n" +
            "GROUP BY alb.name, comp.name, comp.duration, alb.id\n" +
            "HAVING comp.duration = (\n" +
            "    SELECT MIN(cm.duration)\n" +
            "    FROM compositions cm\n" +
            "    WHERE cm.album_id = alb.id\n" +
            "    AND cm.duration >= '00:05:00'\n" +
            ");";
}
