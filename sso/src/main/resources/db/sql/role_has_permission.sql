create table role_has_permission (
    id bigserial not null,
    role_id bigint not null,
    permission_id bigint not null,

    CONSTRAINT pk_role_has_permission primary key (id),

    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role(id),
    CONSTRAINT fk_permission_id FOREIGN KEY (permission_id) REFERENCES permission(id)
)