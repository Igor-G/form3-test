create table payment (
  id varchar(255) not null,
  organization_id varchar(255),
  payment_type integer,
  version integer,
  payment_attributes_id varchar(255),
  primary key (id)
);

create table payment_attributes (
  id varchar(255) not null,
  amount decimal(19,2),
  account_name varchar(255),
  account_number decimal(19,2),
  account_number_code integer,
  account_type integer,
  address varchar(255),
  bank_id decimal(19,2),
  bank_id_code integer,
  name varchar(255),
  currency varchar(255),
  primary key (id)
);

alter table payment add constraint payment_payment_attributes_fk_unique unique (payment_attributes_id);
alter table payment add constraint payment_payment_attributes_fk foreign key (payment_attributes_id) references payment_attributes;