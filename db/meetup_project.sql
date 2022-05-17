create table members (
    id integer primary key,
    first_name text,
    last_name text,
    email text
);
create table groups (
    -- id integer primary key,
    id integer
    name text,
    created_at timestamp
);
create table venues (
    id integer primary key,
    name text,
    postal_code text,
    prefecture text,
    city text,
    street1 text,
    street2 text,
    building text
);
create table meetups (
    id integer primary key,
    title text not null,
    start_at timestamp,
    end_at timestamp,
    venue_id int,
    group_id int,
    foreign key(venue_id) references venues(id),
    foreign key(group_id) references groups(id)
);
create table member_follow (
    created_by int,
    member_id int,
    foreign key(created_by) references members(id),
    foreign key(member_id) references members(id),
    primary key (created_by, member_id)
);
create table meetups_members (
    meetup_id int,
    member_id int,
    foreign key(meetup_id) references meetups(id),
    foreign key(member_id) references members(id),
    primary key (meetup_id, member_id)
);
create table groups_members (
    group_id int,
    member_id int,
    foreign key(member_id) references members(id),
    foreign key(group_id) references groups(id),
    primary key (group_id, member_id)
);
