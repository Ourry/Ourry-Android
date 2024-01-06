package com.worldonetop.ourry.ui.screen.home

import java.util.Date

data class TEMP_Board (
    val title: String,
    val writer: String,
    val responseCnt: Int,
    val commentCnt: Int,
    val time: Date,
)

val DATA_Board = listOf(
    TEMP_Board("전세집을 구하고 있어요", "홍길동1",0, 0, Date().also { it.time -= 1000 * 60 * 56   }),
    TEMP_Board("전세 vs 월세, 어떤 게 나을까요?", "홍길동2",5, 5, Date().also { it.time -= 1000 * 60 * 60 * 23  }),
    TEMP_Board("현금을 모을지, 주식을 '줍줍'할지 고민돼요", "홍길동3",10, 72, Date().also { it.time -= 1000L * 60 * 60 * 24  * 2 }),
    TEMP_Board("대학원 진학에 대한 진지한 고민이 있습니다. 간판의 영향은 어느정도인가요?", "홍길동4",111, 222, Date().also { it.time -= 1000L * 60 * 60 * 24 * 30 * 2  }),
    TEMP_Board("긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트긴글 테스트", "홍길동5",5555, 5555, Date().also { it.time -= 1000L * 60 * 60 * 24 * 30 * 12*1  }),
    TEMP_Board("줄넘김테스트\n\n\n\n\n\n\n\n\n줄넘김테스트", "홍길동6",66666,66666, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동7",777777, 777777, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동8",10, 72, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동9",10, 72, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동10",10, 72, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동11",10, 72, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동12",10, 72, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동13",10, 72, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동14",10, 72, Date().also { it.time -= 1000 * 60 * 56  }),
    TEMP_Board("전세집을 구하고 있어요", "홍길동15",10, 72, Date().also { it.time -= 1000 * 60 * 56  }),
)
val DATA_Category = listOf("전체", "내 질문", "가정/육아", "결혼/연애", "부동산/경제", "사회생활", "학업", "커리어")
