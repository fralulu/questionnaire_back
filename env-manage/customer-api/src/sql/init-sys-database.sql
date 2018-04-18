/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/12 17:08:56                           */
/*==============================================================*/


drop table if exists sys_menu;

drop table if exists sys_menu_role;

drop table if exists sys_role_resource;

drop table if exists sys_roles;

drop table if exists sys_tenant;

drop table if exists sys_tenant_resource;

drop table if exists sys_user;

drop table if exists sys_user_role;

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   int(11) not null auto_increment,
   pid                  int(11) comment '父级id',
   pids                 varchar(100) comment '所有父id',
   name                 varchar(50) comment '菜单名',
   ulr                  varchar(256) comment '菜单url',
   is_show              char(2) not null default 'Y' comment '是否显示(Y/N)',
   remarks              varchar(100) comment '备注',
   create_date          datetime default CURRENT_TIMESTAMP,
   update_date          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

alter table sys_menu comment '功能菜单';

/*==============================================================*/
/* Table: sys_menu_role                                         */
/*==============================================================*/
create table sys_menu_role
(
   menu_id              int(11) not null comment '菜单id',
   user_id              int(11) not null comment '用户id',
   create_date          datetime default CURRENT_TIMESTAMP,
   update_date          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (menu_id, user_id)
);

alter table sys_menu_role comment '菜单角色对应表';

/*==============================================================*/
/* Table: sys_role_resource                                     */
/*==============================================================*/
create table sys_role_resource
(
   id                   int(11) not null auto_increment,
   role_id              varchar(50) comment '角色id',
   resource_id          int(11) default NULL comment '角色对应的资源id(设备、站点等)',
   create_date          datetime default CURRENT_TIMESTAMP,
   update_date          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

alter table sys_role_resource comment '角色资源对应表';

/*==============================================================*/
/* Table: sys_roles                                             */
/*==============================================================*/
create table sys_roles
(
   id                   int(11) not null auto_increment,
   role_name            varchar(128) comment '角色名',
   remarks              varchar(500) comment '备注',
   creater_id           int(11) comment '创建人ID',
   create_date          datetime default CURRENT_TIMESTAMP,
   update_date          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

alter table sys_roles comment '角色表';

/*==============================================================*/
/* Table: sys_tenant                                            */
/*==============================================================*/
create table sys_tenant
(
   id                   int(11) not null auto_increment,
   name                 varchar(256) comment '租户名',
   custom_code          varchar(50) comment '客户编码',
   domain               varchar(128) default NULL comment '域名',
   create_date          datetime default CURRENT_TIMESTAMP,
   update_date          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

alter table sys_tenant comment '租户表';

/*==============================================================*/
/* Table: sys_tenant_resource                                   */
/*==============================================================*/
create table sys_tenant_resource
(
   id                   int(11) not null auto_increment,
   pid                  int(11) comment '父级id',
   pids                 varchar(100) comment '所有父id',
   name                 varchar(50) comment '菜单名',
   ulr                  varchar(256) comment '资源访问url',
   is_show              char(2) not null default 'Y' comment '是否显示(Y/N)',
   remarks              varchar(100) comment '备注',
   creater_id           int(11) comment '创建人ID',
   create_date          datetime default CURRENT_TIMESTAMP,
   update_date          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

alter table sys_tenant_resource comment '租户定制资源表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int(11) not null auto_increment,
   tenant_id            int(11) comment '租户id',
   user_name            varchar(20) comment '用户名',
   password             varchar(100) comment '密码',
   mobile               varchar(12) comment '手机号',
   email                varchar(100) comment '邮箱号',
   login_flag           char(2) default 'Y' comment '是否可登陆(Y/N)',
   login_date           datetime comment '登陆时间',
   pwd_last_date        datetime not null comment '密码最近修改时间',
   creater_id           int(11) comment '创建人ID',
   create_date          datetime default CURRENT_TIMESTAMP,
   update_date          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
);

alter table sys_user comment '用户表';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   role_id              int(11) not null comment '角色id',
   user_id              int(11) not null comment '用户id',
   create_date          datetime default CURRENT_TIMESTAMP,
   update_date          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (role_id, user_id)
);

alter table sys_user_role comment '用户角色对应表';

