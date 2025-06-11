package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysUserEntity;
import net.grape.system.query.SysRoleUserQuery;
import net.grape.system.query.SysUserQuery;
import net.grape.system.vo.SysUserAvatarVO;
import net.grape.system.vo.SysUserBaseVO;
import net.grape.system.vo.SysUserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户管理
 *
 * @author 1161095164@qq.com
 *
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    PageResult<SysUserVO> page(SysUserQuery query);

    void save(SysUserVO vo);

    void update(SysUserVO vo);

    void updateLoginInfo(SysUserBaseVO vo);

    void updateAvatar(SysUserAvatarVO avatar);

    void delete(List<Long> idList);

    /**
     * 获取用户姓名列表
     *
     * @param idList 用户ID列表
     * @return 用户姓名列表
     */
    List<String> getRealNameList(List<Long> idList);

    SysUserVO getByMobile(String mobile);

    /**
     * 修改密码
     *
     * @param id          用户ID
     * @param newPassword 新密码
     */
    void updatePassword(Long id, String newPassword);

    /**
     * 分配角色，用户列表
     */
    PageResult<SysUserVO> roleUserPage(SysRoleUserQuery query);

    /**
     * 批量导入用户
     *
     * @param file     excel文件
     * @param password 密码
     */
    void importByExcel(MultipartFile file, String password);

    /**
     * 导出用户信息表格
     */
    void export();
}
