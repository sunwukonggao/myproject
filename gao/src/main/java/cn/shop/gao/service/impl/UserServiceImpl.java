package cn.shop.gao.service.impl;

import cn.shop.gao.dao.GroupDao;
import cn.shop.gao.dao.UserDao;
import cn.shop.gao.dao.UserGroupDao;
import cn.shop.gao.domain.User;
import cn.shop.gao.service.UserService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service("userserviceimpl")
public class UserServiceImpl implements UserService {
    private UserDao userdao;
    private GroupDao groupDao;
    private UserGroupDao userGroupDao;
    private MemcachedClient memcachedClient;
    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    @Resource(name = "redisTemplate")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    @Resource(name = "memcachedClient")
    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public UserDao getUserdao() {
        return userdao;
    }

    @Resource(name = "userdaoimpl")
    public void setUserdao(UserDao userdao) {
        this.userdao = userdao;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    @Resource(name = "groupDaoimpl")
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public UserGroupDao getUserGroupDao() {
        return userGroupDao;
    }

    @Resource(name = "userGroupDaoimpl")
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    public List<User> getAllUser() {

        try {
            if (memcachedClient.get("all") == null) {
                memcachedClient.add("all", 10000, userdao.findALLUser());
                return userdao.findALLUser();
            } else {
                return memcachedClient.get("all");
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Serializable> getAllUserredis() {
        if (redisTemplate.opsForList().range("all", 0, -1).size() == 0) {
            redisTemplate.opsForList().leftPushAll("all", userdao.findALLUser());
            return redisTemplate.opsForList().range("all", 0, -1);
        } else {
            return redisTemplate.opsForList().range("all", 0, -1);
        }
    }

    public User getUser() {
        try {
            if (memcachedClient.get("all") == null) {
                memcachedClient.add("all", 1, userdao.findALLUser().get(1));
                return userdao.findALLUser().get(1);
            } else {
                return memcachedClient.get("all");
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User getUser(User user) {
        return userdao.findUser(user);
    }


    public List<User> getUserByPage(String sord, String sidx, int from, int length) {
        return userdao.findALLUser(sord, sidx, from, length);
    }


    public int getUserCount() {
        return userdao.findUserCount();
    }

    public String getUserRight(Integer user_id) {
        List<String> groupRight = userGroupDao.getGroupRight(user_id);
        int rightSize = groupRight.size();
        if (rightSize == 1) {
            return groupRight.get(0);
        } else if (rightSize > 1) {
            Integer temp = 0;
            for (int i = 0; i < rightSize; i++) {
                temp = temp | Integer.parseInt(groupRight.get(i), 2);
            }
            return Integer.toBinaryString(temp);

        }
        return null;

    }

    public Map<InetSocketAddress, Map<String, String>> testxm() {
        try {
            return memcachedClient.getStats();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}

