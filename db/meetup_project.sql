CREATE TABLE members (
    id INTEGER PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    email TEXT
);
CREATE TABLE groups (
    id INTEGER PRIMARY KEY,
    name TEXT,
    created_at TIMESTAMP
);
CREATE TABLE venues (
    id INTEGER PRIMARY KEY,
    name TEXT,
    postal_code TEXT,
    prefecture TEXT,
    city TEXT,
    street1 TEXT,
    street2 TEXT,
    building TEXT
);
CREATE TABLE meetups (
    id INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    start_at TIMESTAMP,
    end_at TIMESTAMP,
    venue_id INT,
    group_id INT,
    FOREIGN KEY(venue_id) REFERENCES venues(id),
    FOREIGN KEY(group_id) REFERENCES groups(id)
);
CREATE INDEX idx_meetups_venue_id ON meetups(venue_id);
CREATE INDEX idx_meetups_group_id ON meetups(group_id);
CREATE TABLE member_follow (
    created_by INT,
    member_id INT,
    FOREIGN KEY(created_by) REFERENCES members(id),
    FOREIGN KEY(member_id) REFERENCES members(id),
    PRIMARY KEY(created_by, member_id)
);
CREATE TABLE meetups_members (
    meetup_id INT,
    member_id INT,
    FOREIGN KEY(meetup_id) REFERENCES meetups(id),
    FOREIGN KEY(member_id) REFERENCES members(id),
    PRIMARY KEY(meetup_id, member_id)
);
CREATE TABLE groups_members (
    group_id INT,
    member_id INT,
    FOREIGN KEY(member_id) REFERENCES members(id),
    FOREIGN KEY(group_id) REFERENCES groups(id),
    PRIMARY KEY(group_id, member_id)
);