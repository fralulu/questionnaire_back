ALTER TABLE `sys_user`
ADD COLUMN `logout_flag`  char(2) NULL AFTER `login_flag`,
ADD COLUMN `force_login`  char(2) NULL AFTER `logout_flag`


ALTER TABLE `sys_user`
MODIFY COLUMN `logout_flag`  char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登出标志' AFTER `login_flag`,
MODIFY COLUMN `force_login`  char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '强制重新登陆(Y/N)' AFTER `logout_flag`