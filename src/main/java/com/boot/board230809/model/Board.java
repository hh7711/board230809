package com.boot.board230809.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull //제목 필수값
//    @Size(min = 2, max = 30) //최소값 2, 최댓값 30
    @Size(min = 2, max = 30, message = "제목은 2자이상 30자 이하로 입력하세요.") //최소값 2, 최댓값 30
    private String title;


    private String content;
}
