package com.gde.integral.service.cocreation.service.impl;

import com.gde.integral.service.cocreation.dao.CoCreationActivityDao;
import com.gde.integral.service.cocreation.pojo.CoCreationActivity;
import com.gde.integral.service.cocreation.service.CoCreationActivityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * 共创活动服务实现类
 *
 * @author ~
 * @date 2019/7/5
 */
@Service
public class CoCreationActivityServiceImpl implements CoCreationActivityService {

    private final CoCreationActivityDao coCreationActivityDao;

    @Autowired
    public CoCreationActivityServiceImpl(CoCreationActivityDao coCreationActivityDao) {
        this.coCreationActivityDao = coCreationActivityDao;
    }

    /**
     * 分页 + 条件查询
     *
     * @param searchMap 查询参数Map
     * @param page 指定的查询页码
     * @param size 每页展示数据条数
     * @return 共创活动List集合
     */
    @Override
    public Page<CoCreationActivity> findBySpec(Map searchMap, int page, int size) {
        Specification<CoCreationActivity> spec = createSpec(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return coCreationActivityDao.findAll(spec, pageRequest);
    }

    /**
     * 构建查询条件
     *
     * @param searchMap 查询参数Map
     * @return 构建好的查询条件
     */
    private Specification<CoCreationActivity> createSpec(Map searchMap) {

        String underway = "UNDERWAY";
        String finished = "FINISHED";
        String status = "status";
        String category = "category";
        String visible = "visible";

        String activityStatus = "";
        String activityCategory = "";

        // 从查询参数Map中获取是否在前端展示的参数
        String activityVisible = "";

        if (searchMap.get(status) != null) {
            activityStatus = searchMap.get("status").toString();
        }
        if (searchMap.get(category) != null) {
            activityCategory = searchMap.get("category").toString();
        }
        if (searchMap.get(visible) != null) {
            activityVisible = searchMap.get(visible).toString();
        }

        // 当前时间
        Date currentTime = new Date();

        String finalActivityStatus = activityStatus;
        String finalActivityCategory = activityCategory;
        String finalActivityVisible = activityVisible;

        return (Specification<CoCreationActivity>) (root, query, cb) -> {

            List<Predicate> predicateList = new ArrayList<>();

            // 活动状态
            if (StringUtils.isNotBlank(finalActivityStatus) && underway.equals(finalActivityStatus)) {
                // 活动进行中
                Predicate p1 = cb.lessThan(root.get("startDate"), currentTime);
                Predicate p2 = cb.greaterThan(root.get("endDate"), currentTime);
                predicateList.add(cb.and(p1, p2));
            } else if (StringUtils.isNotBlank(finalActivityStatus) && finished.equals(finalActivityStatus)) {
                // 活动已结束
                Predicate p1 = cb.greaterThan(root.get("startDate"), currentTime);
                Predicate p2 = cb.lessThan(root.get("endDate"), currentTime);
                predicateList.add(cb.or(p1, p2));
            }

            // 活动类型
            if (StringUtils.isNotBlank(finalActivityCategory)) {
                predicateList.add(cb.equal(root.get("category"), finalActivityCategory));
            }

            // 是否在前端展示
            if (StringUtils.isNotBlank(finalActivityVisible)) {
                predicateList.add(cb.equal(root.get("visible"), finalActivityVisible));
            }

            // 根据活动开始日期排序(倒序)
            query.orderBy(cb.desc(root.get("startDate")));

            query.where(cb.and(predicateList.toArray(new Predicate[0])));

            return query.getRestriction();
        };
    }

    /**
     * 根据主键查询
     *
     * @param tid 共创活动主键
     * @return 共创活动对象
     */
    @Override
    public CoCreationActivity findById(String tid) {
        Optional<CoCreationActivity> coCreationActivityOptional = coCreationActivityDao.findById(tid);
        return coCreationActivityOptional.orElse(null);
    }

    /**
     * 更新修改的共创活动对象
     *
     * @param coCreationActivity 共创活动对象
     */
    @Override
    public void update(CoCreationActivity coCreationActivity) {
        coCreationActivityDao.save(coCreationActivity);
    }

    /**
     * 获取所有活动类型
     *
     * @return 活动类型List集合
     */
    @Override
    public List<String> getCategories() {
        return coCreationActivityDao.getCategories();
    }

    /**
     * 获取活动数量和活动参与人数
     *
     * @return 存放结果的Map集合
     */
    @Override
    public Map getActivitiesInfo() {
        Map<String, Integer> resultMap = new HashMap<>(4);
        int activityCount = coCreationActivityDao.countByVisible("true");
        int activityPopulation = coCreationActivityDao.sumReplyNumByVisible("true");
        resultMap.put("activityCount", activityCount);
        resultMap.put("activityPopulation", activityPopulation);
        return resultMap;
    }
    
}
