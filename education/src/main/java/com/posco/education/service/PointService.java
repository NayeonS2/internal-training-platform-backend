package com.posco.education.service;

import com.posco.education.domain.dto.PointAddResponse;
import com.posco.education.domain.entity.Point;
import com.posco.education.domain.entity.Review;
import com.posco.education.domain.entity.User;
import com.posco.education.repository.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public PointService(UserRepository userRepository, PointRepository pointRepository, QuizRepository quizRepository) {
        this.userRepository = userRepository;
        this.pointRepository = pointRepository;
        this.quizRepository = quizRepository;
    }

    @Transactional
    public Point pointByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        System.out.println(user);

        if (user.getPoint() == null) {
            Point newPoint = new Point();
            user.updateUser(0,newPoint);
            pointRepository.save(newPoint);
            userRepository.save(user);
        }

        return user.getPoint();
    }

    @Transactional
    public PointAddResponse addPoint(String userId, String topic) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));


        Point userPoint = pointByUser(userId);

        if (userPoint.getFinanceP() == null) {
            userPoint.setFinanceP(0);
        }
        if (userPoint.getItP() == null) {
            userPoint.setItP(0);
        }
        if (userPoint.getMarketingP() == null) {
            userPoint.setMarketingP(0);
        }
        if (userPoint.getLanguageP() == null) {
            userPoint.setLanguageP(0);
        }
        if (userPoint.getProductionP() == null) {
            userPoint.setProductionP(0);
        }



        Integer finance_p = userPoint.getFinanceP();
        Integer it_p = userPoint.getItP();
        Integer language_p = userPoint.getLanguageP();
        Integer marketing_p = userPoint.getMarketingP();
        Integer production_p = userPoint.getProductionP();

        if(topic.equals("재무")) {
            finance_p += 1;
        } else if (topic.equals("IT")) {
            it_p += 1;
        } else if (topic.equals("언어")) {
            language_p += 1;
        } else if (topic.equals("마케팅")) {
            marketing_p += 1;
        } else if (topic.equals("생산기술")) {
            production_p += 1;
        }

        userPoint.updatePoint(language_p, production_p, finance_p, marketing_p, it_p);
        pointRepository.save(userPoint);

        PointAddResponse pointAddResponse = new PointAddResponse();

        Integer sum_p = language_p+production_p+finance_p+marketing_p+it_p;

        Integer now_lv = userRepository.findById(userId)
                .map(User::getQuiz_lv)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Integer update_lv = 0;
        Integer is_lvup = 0;

        if (sum_p >= 15) {
            update_lv = 3;
        } else if (sum_p >= 10) {
            update_lv = 2;
        } else if (sum_p >= 5) {
            update_lv = 1;
        }
        System.out.println(now_lv);
        System.out.println(update_lv);
        System.out.println(update_lv==now_lv);

        is_lvup = (update_lv==now_lv) ? 0 : 1;

        user.updateUser(update_lv, userPoint);
        userRepository.save(user);

        String username = user.getUser_name();

        pointAddResponse.setFinance_p(finance_p);
        pointAddResponse.setProduction_p(production_p);
        pointAddResponse.setMarketing_p(marketing_p);
        pointAddResponse.setLanguage_p(language_p);
        pointAddResponse.setIt_p(it_p);
        pointAddResponse.setSum_p(sum_p);
        pointAddResponse.setUser(username);
        pointAddResponse.setUser_lv(update_lv);
        pointAddResponse.setIs_lvup(is_lvup);

        return pointAddResponse;
    }
}
