package com.posco.education.domain.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer point_id;     // 아이디
    @Column(name = "language_p")
    @Builder.Default
    private Integer languageP=0;     // 언어 포인트
    @Column(name = "production_p")
    @Builder.Default
    private Integer productionP=0;     // 생산 포인트
    @Column(name = "finance_p")
    @Builder.Default
    private Integer financeP=0;     // 재무 포인트
    @Column(name = "marketing_p")
    @Builder.Default
    private Integer marketingP=0;     // 마케팅 포인트
    @Column(name = "it_p")
    @Builder.Default
    private Integer itP=0;     // it 포인트




    public void updatePoint (Integer languageP, Integer productionP, Integer financeP, Integer marketingP, Integer itP) {
        this.languageP = languageP;
        this.productionP = productionP;
        this.financeP = financeP;
        this.marketingP = marketingP;
        this.itP = itP;
    }


}
