
-- -----------------------------------------------------
-- Table `iot_cfg_telemeter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iot_cfg_telemeter` ;

CREATE TABLE IF NOT EXISTS `iot_cfg_telemeter` (
  `tenant_id` INT(11) NOT NULL COMMENT '租户编号',
  `site_id` int(11) NOT NULL COMMENT '站点号',
  `device_id` BIGINT(64) NOT NULL COMMENT '设备号',
  `mete_index` SMALLINT NOT NULL COMMENT '同一设备下监控量序号',
  `mete_id` INT NOT NULL COMMENT '监控量编号',
  `mete_name` VARCHAR(40) NOT NULL COMMENT '遥测量名称',
  `bas_mete_id` CHAR(15) NOT NULL COMMENT '对应的基本遥测量类型',
  `mete_kind` TINYINT NOT NULL COMMENT '监控量类型',
  `up_effect` DOUBLE NULL DEFAULT 0 COMMENT '有效上限',
  `low_effect` DOUBLE NULL DEFAULT 0 COMMENT '有效下限',
  `accuracy` TINYINT NOT NULL COMMENT '小数点后有效位数',
  `unit` VARCHAR(8) NULL COMMENT '单位',
  `valid_mid` VARCHAR(255) NULL COMMENT '有效性表达式变量串',
  `valid_string` VARCHAR(255) NULL COMMENT '有效性判断表达式',
  `invalid_value` DOUBLE NULL DEFAULT 0 COMMENT '无效时的值',
  `last_value` DOUBLE NULL DEFAULT 0 COMMENT '当前值',
  `last_time` DATETIME NULL COMMENT '当前值的更新时间',
  `fsu_id` BIGINT(64) NULL DEFAULT 0 COMMENT 'FSU设备编号',
  `channel_no` SMALLINT NULL DEFAULT 0 COMMENT 'FSU的通道号',
  PRIMARY KEY (`tenant_id`, `device_id`, `mete_id`))
COMMENT = '遥测量配置表';


-- -----------------------------------------------------
-- Table `iot_cfg_telesignal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iot_cfg_telesignal` ;

CREATE TABLE IF NOT EXISTS `iot_cfg_telesignal` (
  `tenant_id` INT(11) NOT NULL COMMENT '租户编号',
  `site_id` int(11) NOT NULL COMMENT '站点号',
  `device_id` BIGINT(64) NOT NULL COMMENT '设备号',
  `mete_index` SMALLINT NOT NULL COMMENT '同一设备下监控量序号',
  `mete_id` INT NOT NULL COMMENT '遥信量编号',
  `mete_name` VARCHAR(40) NOT NULL COMMENT '遥信量名称',
  `bas_mete_id` CHAR(15) NOT NULL COMMENT '对应的基本遥信量',
  `mete_kind` TINYINT NOT NULL COMMENT '监控量类型',
  `signal_kind` TINYINT NOT NULL COMMENT '遥信量种类',
  `up_effect` TINYINT NULL DEFAULT 5 COMMENT '有效上限',
  `last_value` TINYINT NULL DEFAULT 0 COMMENT '当前值',
  `last_time` DATETIME NULL COMMENT '更新时间',
  `report_type` TINYINT NULL DEFAULT 1 COMMENT '上报模式',
  `report_level` TINYINT NULL DEFAULT 1 COMMENT '上报级别',
  `mask_type` TINYINT NULL DEFAULT 0 COMMENT '屏蔽类型',
  `mask_mid` VARCHAR(255) NULL COMMENT '告警屏蔽表达式变量串',
  `mask_string` VARCHAR(255) NULL COMMENT '告警屏蔽表达式',
  `mask_value` TINYINT NULL COMMENT '屏蔽值',
  `delay_time` INT NULL DEFAULT 0 COMMENT '防抖延时门限',
  `fsu_id` BIGINT(64) NULL DEFAULT 0 COMMENT 'FSU设备编号',
  `channel_no` SMALLINT NULL COMMENT 'FSU的通道号',
  PRIMARY KEY (`tenant_id`, `device_id`, `mete_id`))
COMMENT = '遥信量配置表';


-- -----------------------------------------------------
-- Table `iot_cfg_teleadjust`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iot_cfg_teleadjust` ;

CREATE TABLE IF NOT EXISTS `iot_cfg_teleadjust` (
  `tenant_id` INT(11) NOT NULL COMMENT '租户编号',
  `site_id` int(11) NOT NULL COMMENT '站点编号',
  `device_id` BIGINT(64) NOT NULL COMMENT '设备编号',
  `mete_index` SMALLINT NOT NULL COMMENT '同一设备下监控量序号',
  `mete_id` INT NOT NULL COMMENT '监控量编号',
  `mete_name` VARCHAR(40) NOT NULL COMMENT '遥调量名称',
  `bas_mete_id` CHAR(15) NOT NULL COMMENT '对应的基本遥调量',
  `mete_kind` TINYINT NOT NULL COMMENT '监控量类型',
  `up_effect` DOUBLE NOT NULL COMMENT '有效上限',
  `low_effect` DOUBLE NOT NULL COMMENT '有效下限',
  `accuracy` TINYINT NOT NULL COMMENT '小数点后有效位数',
  `unit` VARCHAR(8) NULL COMMENT '单位',
  `last_value` DOUBLE NULL DEFAULT 0 COMMENT '当前值',
  `last_time` DATETIME NULL COMMENT '更新时间',
  `fsu_id` BIGINT(64) NULL DEFAULT 0 COMMENT 'FSU编号',
  `channel_no` SMALLINT NULL DEFAULT 0 COMMENT 'FSU的通道号',
  PRIMARY KEY (`tenant_id`, `device_id`, `mete_id`))
COMMENT = '遥调量配置表';


-- -----------------------------------------------------
-- Table `iot_cfg_telecontrol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iot_cfg_telecontrol` ;

CREATE TABLE IF NOT EXISTS `iot_cfg_telecontrol` (
  `tenant_id` INT(11) NOT NULL COMMENT '租户编号',
  `site_id` int(11) NOT NULL COMMENT '站点编号',
  `device_id` BIGINT(64) NOT NULL COMMENT '设备编号',
  `mete_index` SMALLINT NOT NULL COMMENT '同一设备下监控量序号',
  `mete_id` INT NOT NULL COMMENT '监控量编号',
  `mete_name` VARCHAR(40) NOT NULL COMMENT '监控量名称',
  `bas_mete_id` CHAR(15) NOT NULL COMMENT '基本遥控量类型',
  `mete_kind` TINYINT NOT NULL COMMENT '监控量类型',
  `control_status` TINYINT NULL DEFAULT 0 COMMENT '可控状态',
  `enable_string` VARCHAR(255) NULL COMMENT '控制使能条件表达式',
  `succeed_string` VARCHAR(255) NULL,
  `trigger_string` VARCHAR(255) NULL COMMENT '触发条件表达式',
  `fsu_id` BIGINT(64) NULL DEFAULT 0 COMMENT 'FSU设备编号',
  `control_value` TINYINT NULL DEFAULT 0 COMMENT '控制参数',
  `channel_no` SMALLINT NULL DEFAULT 0 COMMENT 'FSU的通道号',
  PRIMARY KEY (`tenant_id`, `device_id`, `mete_id`))
COMMENT = '遥控量配置表';


-- -----------------------------------------------------
-- Table `iot_cfg_alarmmask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iot_cfg_alarmmask` ;

CREATE TABLE IF NOT EXISTS `iot_cfg_alarmmask` (
  `tenant_id` INT(11) NOT NULL COMMENT '租户编号',
  `site_id` INT(11) NOT NULL COMMENT '站点编号',
  `device_id` BIGINT(64) NOT NULL COMMENT '设备编号',
  `mete_id` INT(11) NOT NULL COMMENT '监控量编号',
  `mask_type` TINYINT NULL DEFAULT 0 COMMENT '屏蔽方式',
  `sunday` CHAR(13) NOT NULL COMMENT '星期天屏蔽时段',
  `monday` CHAR(13) NOT NULL COMMENT '星期一屏蔽时段',
  `tuesday` CHAR(13) NOT NULL COMMENT '星期二屏蔽时段',
  `wednesday` CHAR(13) NOT NULL COMMENT '星期三屏蔽时段',
  `thursday` CHAR(13) NOT NULL COMMENT '星期四屏蔽时段',
  `friday` CHAR(13) NOT NULL COMMENT '星期五屏蔽时段',
  `saturday` CHAR(13) NOT NULL COMMENT '星期六屏蔽时段',
  PRIMARY KEY (`tenant_id`, `device_id`, `mete_id`))
COMMENT = '告警时段屏蔽表';


-- -----------------------------------------------------
-- Table `iot_cfg_express`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `iot_cfg_express` ;
-- 
-- CREATE TABLE IF NOT EXISTS `iot_cfg_express` (
--   `express_id` INT NOT NULL AUTO_INCREMENT COMMENT '操作编号',
--   `express_name` VARCHAR(50) NOT NULL COMMENT '操作名称',
--   `express_value` VARCHAR(512) NOT NULL COMMENT '表达式及公式内容',
--   `device_type` SMALLINT NOT NULL COMMENT '设备类型',
--   `channel_no` INT NULL COMMENT '通道号',
--   `mete_type` TINYINT NULL COMMENT '监控量类型',
--   `control_type` TINYINT NULL COMMENT '控制类型',
--   `meteid_set` VARCHAR(1000) NULL COMMENT '监控量编码集合',
--   `express_type` TINYINT NOT NULL COMMENT '类型',
--   `is_system` TINYINT NOT NULL COMMENT '是否系统表达式',
--   PRIMARY KEY (`express_id`),
--   UNIQUE INDEX `express_id_UNIQUE` (`express_id` ASC))
-- COMMENT = '表达式及计算公式固化表';





drop table iot_cfg_precinct;
/*==============================================================*/
/* Table: iot_cfg_precinct                                      */
/*==============================================================*/
create table iot_cfg_precinct
(
   tenant_id            Int(11) not null comment '租户编号',
   precinct_id          int not null AUTO_INCREMENT comment '管辖区编号',
   precinct_name        varchar(40) not null comment '管辖区名称',
   up_precinct_id       int default 0 comment '上级管理区编号',
   primary key (precinct_id)
);

alter table iot_cfg_precinct comment '管辖区配置信息';


drop table iot_cfg_building;
/*==============================================================*/
/* Table: iot_cfg_building                                      */
/*==============================================================*/
create table iot_cfg_building
(
   tenant_id            Int(11) not null comment '租户编号',
   precinct_id          int(11) not null comment '管辖区编号',
   building_id          int(11) not null AUTO_INCREMENT comment '楼栋编号',
   building_name        varchar(40) not null comment '楼栋名称',
   primary key (building_id)
);

alter table iot_cfg_building comment '楼栋信息表';

drop table iot_cfg_floor;
/*==============================================================*/
/* Table: iot_cfg_floor                                         */
/*==============================================================*/
create table iot_cfg_floor
(
   precinct_id          int(11) not null comment '楼栋编号',
   floor_id             int(11) not null AUTO_INCREMENT comment '楼层编号',
   floor_name           varchar(40) not null comment '栋层室名称',
   primary key (floor_id)
);

alter table iot_cfg_floor comment '楼层信息表';

drop table iot_cfg_room;
/*==============================================================*/
/* Table: iot_cfg_room                                          */
/*==============================================================*/
create table iot_cfg_room
(
   floor_id             int(11) not null comment '楼层编号',
   room_id              int(11) not null AUTO_INCREMENT comment '楼室编号',
   room_name            varchar(40) not null comment '栋层室名称',
   primary key (room_id)
);

alter table iot_cfg_room comment '楼室信息表';


drop table iot_cfg_site;
/*==============================================================*/
/* Table: iot_cfg_site                                          */
/*==============================================================*/
create table iot_cfg_site
(
   tenant_id            Int(11) not null comment '租户编号',
   site_id              Int(11) not null AUTO_INCREMENT comment '同一tenant下的站点号',
   site_name            varchar(40) not null comment '站点名称',
   site_kind            tinyint not null comment '站点类型',
   precinct_id          Int(11) default 0 comment '管辖区编号',
   room_id              Int(11) default 0 comment '楼室编号',
   floor_id             Int(11) default 0 comment '楼层编号',
   building_id          Int(11) default 0 comment '楼栋编号',
   user_site_id         varchar(40) comment '用户站点编号',
   is_templet           tinyint default 0 comment '是否为站点模板',
   net_type             tinyint default 0 comment '组网方式',
   run_type             tinyint default 0 comment '在线运行状态',
   primary key (site_id)
);

alter table iot_cfg_site comment '站点配置信息';



/*==============================================================*/
/* Table: iot_bas_explain                                       */
/*==============================================================*/
drop table iot_bas_explain;
create table iot_bas_explain
(
   explain_type         smallint(6)  not null comment '遥信量解释类型编号',
   signal_value         tinyint(4)   not null   comment '遥信量值',
   name                 varchar(40) not null comment '解释字符串',
   name_en              varchar(40) not null comment '英文解释字符串',
   primary key (explain_type,signal_value)
);

alter table iot_bas_explain comment '遥信量解释表';

/*==============================================================*/
/* Table: iot_bas_explaintype                                    */
/*==============================================================*/
drop table iot_bas_explaintype;
create table iot_bas_explaintype
(
   explain_type         smallint(6)  not null AUTO_INCREMENT comment '遥信量解释类型编号',
   name                 varchar(40) not null comment '遥信量解释类型名称',
   name_en              varchar(40) not null comment '英文名称',
   primary key (explain_type)
);

alter table iot_bas_explaintype comment '遥信量解释类型表';



-- -----------------------------------------------------
-- Table `iot_bas_teleconfig`2017-12-15
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iot_bas_teleconfig` ;

CREATE TABLE IF NOT EXISTS `iot_bas_teleconfig` (
  `bas_mete_id` CHAR(14) NOT NULL COMMENT '基本配置量编号',
  `bas_mete_name` VARCHAR(50) NOT NULL COMMENT '基本配置量名称',
  `bas_mete_name_en` VARCHAR(50) NULL COMMENT '基本配置量名称',
  `device_type` SMALLINT NOT NULL COMMENT '设备类型',
  `mete_type` SMALLINT NOT NULL COMMENT '配置量类型',
  `same_metetype_index` SMALLINT  NULL DEFAULT 0 COMMENT '同类配置量子序号',
  `metetype_name` VARCHAR(50) NOT NULL COMMENT '配置量类型名称',
  `metetype_name_en` VARCHAR(50) NOT NULL COMMENT '英文名称',
  `mete_kind` TINYINT NULL DEFAULT 5 COMMENT '配置量类型',
  `config_value` VARCHAR(50) NOT NULL COMMENT '配置值',
  `unit` VARCHAR(8) NOT NULL COMMENT '单位',
  `bas_channel_no` SMALLINT  NULL DEFAULT 0 COMMENT 'FSU通道号',
  PRIMARY KEY (`bas_mete_id`, `device_type`, `mete_type`))
COMMENT = '配置量基本表';

-- -----------------------------------------------------
-- Table `iot_cfg_teleconfig`2017-12-15
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iot_cfg_teleconfig` ;

CREATE TABLE IF NOT EXISTS `iot_cfg_teleconfig` (
  `tenant_id` INT(11) NOT NULL COMMENT '租户编号',
  `site_id` int(11) NOT NULL COMMENT '站点编号',
  `device_id` BIGINT(64) NOT NULL COMMENT '设备编号',
  `mete_index` SMALLINT NOT NULL COMMENT '同一设备下监控量序号',
  `mete_id` INT NOT NULL COMMENT '监控量编号',
  `mete_name` VARCHAR(40) NOT NULL COMMENT '监控量名称',
  `bas_mete_id` CHAR(15) NOT NULL COMMENT '基本配置量类型',
  `mete_kind` TINYINT NOT NULL COMMENT '监控量类型',
  `control_status` TINYINT NULL DEFAULT 0 COMMENT '可控状态',
  `enable_string` VARCHAR(255) NULL COMMENT '控制使能条件表达式',
  `succeed_string` VARCHAR(255) NULL,
  `trigger_string` VARCHAR(255) NULL COMMENT '触发条件表达式',
  `fsu_id` BIGINT(64) NULL DEFAULT 0 COMMENT 'FSU设备编号',
  `control_value` TINYINT NULL DEFAULT 0 COMMENT '控制参数',
  `config_value` VARCHAR(50) NOT NULL COMMENT '配置值',
  `unit` VARCHAR(8) NOT NULL COMMENT '单位',
  `channel_no` SMALLINT NULL DEFAULT 0 COMMENT 'FSU的通道号',
  PRIMARY KEY (`tenant_id`, `device_id`, `mete_id`))
COMMENT = '配置量配置表';



-- -----------------------------------------------------
-- Table `iot_cfg_teleconfig`2018-01-26
-- -----------------------------------------------------
 CREATE TABLE IF NOT EXISTS `iot_cfg_servertype` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `server_type` int(11) NOT NULL COMMENT '服务器类型',
  `server_type_name` VARCHAR(100) NOT NULL COMMENT '服务器类型名称',
  `server_type_name_en` VARCHAR(100)  NULL COMMENT '服务器类型英文名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `iot_cfg_servertype_type` (`server_type`) 
  )
COMMENT = '服务器类型表';


-- -----------------------------------------------------
-- Table `iot_cfg_serverconfig`2018-01-26
-- -----------------------------------------------------
 CREATE TABLE IF NOT EXISTS `iot_cfg_serverconfig` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `server_type` int(11) NOT NULL COMMENT '服务器类型',
  `server_name` VARCHAR(100) NOT NULL COMMENT '服务器名称',
  `server_code` VARCHAR(100)  NOT NULL COMMENT '服务器编码',
  `internal_network_ip` VARCHAR(20)  NOT NULL COMMENT '内网ip',
  `internal_network_port` int(11)  NOT NULL COMMENT '内网端口',
  `network_ip` VARCHAR(20)  NOT NULL COMMENT '外网ip',
  `network_port` int(11)  NOT NULL COMMENT '外网端口',
  PRIMARY KEY (`id`)
  )
COMMENT = '服务器配置信息表';



