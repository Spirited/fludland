create table role_has_user (
    id bigserial not null,
    role_id bigint not null,
    user_id integer not null,
    group_id bigint not null,

    CONSTRAINT pk_role_has_user primary key (id),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role(id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_group_id FOREIGN KEY (role_id) REFERENCES user_group(id)
)