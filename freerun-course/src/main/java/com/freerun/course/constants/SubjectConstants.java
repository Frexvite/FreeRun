package com.freerun.course.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class SubjectConstants {

    @AllArgsConstructor
    @Getter
    public enum Type {
        SIGNLE_CHOICE(1, "单选题"),
        MUtiple_CHOICE(2, "多选题"),
        NON_DIRECTIONAL_CHOICE(3, "不定向选择题"),
        JUDGEMENT_QUESTION(4, "判断题");
        private Integer type;
        private String desc;

        public static String desc(Integer subjectType) {
            for (Type type : values()) {
                if (type.type.equals(subjectType)) {
                    return type.desc;
                }
            }
            return null;
        }

    }

    @AllArgsConstructor
    @Getter
    public enum Difficult {
        EASY(1, "简单"), MEDIUM(2, "中等"), DIFFICULT(3, "困难");
        private Integer type;
        private String desc;

        public static String desc(Integer type) {
            for (Difficult difficult : values()) {
                if (difficult.getType().equals(type)) {
                    return difficult.desc;
                }
            }
            return null;
        }
    }
}
