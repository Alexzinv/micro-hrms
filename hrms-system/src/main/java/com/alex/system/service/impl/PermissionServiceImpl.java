package com.alex.system.service.impl;

import cn.hutool.json.JSONObject;
;
import com.alex.common.consant.UserConstant;
import com.alex.system.entity.Permission;
import com.alex.system.entity.RolePermission;
import com.alex.system.entity.User;
import com.alex.system.mapper.PermissionMapper;
import com.alex.system.service.PermissionService;
import com.alex.system.service.RolePermissionService;
import com.alex.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private UserService userService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public Map<String, Object> mapAllPermission() {
        // 查询所有数据
        List<Map<String, Object>> allData = baseMapper.selectMaps(new QueryWrapper<Permission>().orderByAsc("id"));
        return buildMap(allData);
    }

    @Override
    public List<Permission> listAllPermission() {
        // 查询所有数据
        List<Permission> permissions = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("id"));
        return buildList(permissions);
    }

    /**
     * List方式递归查找子节点
     * @param treeNodes 所有菜单
     * @return 菜单树
     */
    private List<Permission> buildList(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if (Objects.equals(0L, treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes 所有菜单
     * @return 菜单树
     */
    private Permission findChildren(Permission treeNode,List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<>());

        for (Permission p : treeNodes) {
            if(treeNode.getId().equals(p.getPid())) {
                int level = treeNode.getLevel() + 1;
                p.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(p,treeNodes));
            }
        }
        return treeNode;
    }

    /**
     * Map方式递归构建菜单
     * @param allData 所有菜单
     * @return 菜单树
     */
    private Map<String, Object> buildMap(List<Map<String, Object>> allData) {
        Map<String, Object> root = new LinkedHashMap<>(64);
        int level = 1;
        for (Map<String, Object> map : allData) {
            Long pid = (Long) map.get("pid");
            if(Objects.equals(0L, pid)){
                // 设置节点级别,根节点level为1,pid为1
                map.put("level", level);
                root = map;
                break;
            }
        }

        // 传入根节点id, 查询子节点, 子节点level+1
        Long id = (Long) root.get("id");
        List<Map<String, Object>> child = getChildWidth(++level, id, allData);
        // List<Map<String, Object>> child = getChildDeep(++level, id, allData);

        root.put("children", child);
        return root;
    }

    /**
     * 递归查找子节点，广度优先
     * @param level 节点层级
     * @param id 父节点id
     * @param allData 所有节点
     * @return 子节点
     */
    private List<Map<String, Object>> getChildWidth(int level, Long id, List<Map<String, Object>> allData) {
        List<Map<String, Object>> child = new ArrayList<>();
        for (Map<String, Object> m : allData) {
            // 将pid与传入的根节点id进行比较
            Long pid = (Long) m.get("pid");
            if(id.equals(pid)){
                m.put("level", level);
                child.add(m);
            }
        }
        if(CollectionUtils.isEmpty(child)){
            return null;
        }
        for (Map<String, Object> m : child) {
            Long mid = (Long) m.get("id");
            m.put("children", getChildWidth(++level, mid, allData));
        }
        return child;
    }

    @Override
    public void removeChildById(Long id) {
        ArrayList<Long> idList = new ArrayList<>();
        getChildById(id, idList);
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    /**
     * 封装所有id
     */
    private void getChildById(Long id, ArrayList<Long> idList) {
        List<Permission> list = baseMapper.selectList(new QueryWrapper<Permission>()
                .eq("pid", id).select("id"));
        list.forEach(item -> {
            idList.add(item.getId());
            getChildById(item.getId(), idList);
        });
    }

    @Override
    public List<Permission> listPermissionByRoleId(Long roleId) {
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>()
                .orderByAsc("CAST(id AS SIGNED)"));

        //根据角色id获取角色权限
        List<RolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>()
                .eq("role_id",roleId));
        //转换给角色id与角色权限对应Map对象
        List<Long> permissionIdList = rolePermissionList.stream()
               .map(RolePermission::getPermissionId).collect(Collectors.toList());

        allPermissionList.forEach(permission -> {
           if(permissionIdList.contains(permission.getId())) {
               permission.setSelect(true);
           }
        });

        return buildList(allPermissionList);
    }

    @Override
    public Map<String, Object> mapPermissionByRoleId(Long roleId) {
        List<Map<String, Object>> permissionList = baseMapper
                .selectMaps(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));

        //根据角色id获取角色权限
        List<Map<String, Object>> rolePermissionList = rolePermissionService
                .listMaps(new QueryWrapper<RolePermission>().eq("role_id", roleId));
        //转换给角色id与角色权限对应Map对象
        for (Map<String, Object> permission : permissionList) {
            for (Map<String, Object> role : rolePermissionList) {
                if(role.get("permissionId").equals(permission.get("id"))){
                    permission.put("isSelect", true);
                }
            }
        }
        return buildMap(permissionList);
    }

    @Override
    public List<String> listPermissionValueByUserId(Long userId) {
        List<String> permissionValueList;
        if(isSysAdmin(userId)) {
            //如果是系统管理员，获取所有权限
            permissionValueList = baseMapper.listAllPermissionValue();
        } else {
            permissionValueList = baseMapper.listPermissionValueByUserId(userId);
        }
        return permissionValueList;
    }

    @Override
    public Map<String, Object> mapPermissionByUserId(Long userId) {
        List<Map<String, Object>> selectPermissionList;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectMaps(null);
        } else {
            selectPermissionList = baseMapper.listPermissionValue(userId);
        }
        return buildMap(selectPermissionList);
    }

    @Override
    public List<JSONObject> listPermissionByUserId(Long userId) {
        List<Permission> selectPermissionList;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.listPermissionByUserId(userId);
        }

        List<Permission> permissionList = buildList(selectPermissionList);
        if(!CollectionUtils.isEmpty(permissionList)){
            permissionList = buildList(selectPermissionList).subList(0,1);
        }
        return buildSideBar(permissionList);
    }

    private boolean isSysAdmin(Long userId){
        User user = userService.getById(userId);
        return user != null && user.getLevel().equals(UserConstant.Level.SYS_ADMIN);
    }

    /**
     * 构建菜单
     * @param treeNodes 权限列表
     * @return 菜单树
     */
    private List<JSONObject> buildSideBar(List<Permission> treeNodes) {
        List<JSONObject> menus = new ArrayList<>(64);
        if(treeNodes.size() == 1) {
            Permission topNode = treeNodes.get(0);
            //左侧一级菜单
            List<Permission> oneMenuList = topNode.getChildren();
            for(Permission one :oneMenuList) {
                JSONObject oneMenu = new JSONObject();
                oneMenu.set("path", one.getPath());
                oneMenu.set("component", one.getComponent());
                oneMenu.set("redirect", "noredirect");
                oneMenu.set("name", "name_"+one.getId());
                oneMenu.set("hidden", false);

                JSONObject oneMeta = new JSONObject();
                oneMeta.set("title", one.getName());
                oneMeta.set("icon", one.getIcon());
                oneMenu.set("meta", oneMeta);

                List<JSONObject> children = new ArrayList<>();
                List<Permission> twoMenuList = one.getChildren();
                for(Permission two :twoMenuList) {
                    JSONObject twoMenu = new JSONObject();
                    twoMenu.set("path", two.getPath());
                    twoMenu.set("component", two.getComponent());
                    twoMenu.set("name", "name_"+two.getId());
                    twoMenu.set("hidden", false);

                    JSONObject twoMeta = new JSONObject();
                    twoMeta.set("title", two.getName());
                    twoMenu.set("meta", twoMeta);

                    children.add(twoMenu);

                    List<Permission> threeMenuList = two.getChildren();
                    for(Permission three :threeMenuList) {
                        if(!StringUtils.hasText(three.getPath())) {
                            continue;
                        }

                        JSONObject threeMenu = new JSONObject();
                        threeMenu.set("path", three.getPath());
                        threeMenu.set("component", three.getComponent());
                        threeMenu.set("name", "name_"+three.getId());
                        threeMenu.set("hidden", true);

                        JSONObject threeMeta = new JSONObject();
                        threeMeta.set("title", three.getName());
                        threeMenu.set("meta", threeMeta);

                        children.add(threeMenu);
                    }
                }
                oneMenu.set("children", children);
                menus.add(oneMenu);
            }
        }
        return menus;
    }
}