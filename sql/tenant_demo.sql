create table sys_user
(
    id        bigint auto_increment comment '主键'
        primary key,
    username  varchar(255) null comment '用户名',
    password  varchar(255) null comment '密码',
    tenant_id bigint       null
)
    comment '用户表';

create table sys_menu
(
    id   bigint auto_increment comment '主键'
        primary key,
    name varchar(255) null comment '菜单名称'
);