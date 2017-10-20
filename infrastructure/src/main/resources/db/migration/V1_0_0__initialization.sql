create table payment (
  id varchar(255) not null,
  organization_id varchar(255),
  version integer,
  payment_attributes_serialized clob,
  primary key (id)
);