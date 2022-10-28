-- 회원
DROP TABLE IF EXISTS member RESTRICT;

-- 공모전
DROP TABLE IF EXISTS contest RESTRICT;

-- 재능판매
DROP TABLE IF EXISTS product RESTRICT;

-- 재능 판매 첨부파일
DROP TABLE IF EXISTS product_file RESTRICT;

-- 공모전 첨부파일
DROP TABLE IF EXISTS contest_file RESTRICT;

-- 공지사항
DROP TABLE IF EXISTS notice RESTRICT;

-- 포트폴리오
DROP TABLE IF EXISTS portfolio RESTRICT;

-- 재능판매카테고리
DROP TABLE IF EXISTS product_category RESTRICT;

-- 1 : 1 문의
DROP TABLE IF EXISTS qna RESTRICT;

-- 문의카테고리
DROP TABLE IF EXISTS qna_category RESTRICT;

-- 후기 게시판
DROP TABLE IF EXISTS product_review RESTRICT;

-- 후기 첨부파일
DROP TABLE IF EXISTS product_review_file RESTRICT;

-- 공모전 카테고리
DROP TABLE IF EXISTS contest_category RESTRICT;

-- 포트폴리오 첨부파일
DROP TABLE IF EXISTS portfolio_file RESTRICT;

-- 1 : 1 문의 첨부파일
DROP TABLE IF EXISTS qna_file RESTRICT;

-- 주최기관
DROP TABLE IF EXISTS contest_organization RESTRICT;

-- 위시리스트
DROP TABLE IF EXISTS wishlist RESTRICT;

-- 팀원모집
DROP TABLE IF EXISTS team RESTRICT;

-- 팀원모집분야지원
DROP TABLE IF EXISTS team_field_member RESTRICT;

-- 모집분야
DROP TABLE IF EXISTS team_field RESTRICT;

-- FAQ
DROP TABLE IF EXISTS faq RESTRICT;

-- 관심사
DROP TABLE IF EXISTS interest RESTRICT;

-- 관리자
DROP TABLE IF EXISTS admin_member RESTRICT;

-- 주문내역
DROP TABLE IF EXISTS product_order RESTRICT;

-- 1대1대화
DROP TABLE IF EXISTS message RESTRICT;

-- 직업
DROP TABLE IF EXISTS job RESTRICT;

-- 1대1대화 첨부파일
DROP TABLE IF EXISTS message_file RESTRICT;

-- 회원
CREATE TABLE member (
  mno         INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  email       VARCHAR(255) NOT NULL COMMENT '이메일(아이디)', -- 이메일(아이디)
  pwd         VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
  name        VARCHAR(255) NOT NULL COMMENT '이름', -- 이름
  nick        VARCHAR(255) NOT NULL COMMENT '닉네임', -- 닉네임
  tel         VARCHAR(30)  NULL     COMMENT '연락처', -- 연락처
  cdt         DATE         NOT NULL DEFAULT now() COMMENT '가입일', -- 가입일
  pstno       VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  base_addr   VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  detail_addr VARCHAR(255) NOT NULL COMMENT '상세주소', -- 상세주소
  jno         INTEGER      NOT NULL COMMENT '직업번호', -- 직업번호
  bank        VARCHAR(255) NULL     COMMENT '은행명', -- 은행명
  account     VARCHAR(100) NULL     COMMENT '계좌번호', -- 계좌번호
  profile     VARCHAR(255) NULL     COMMENT '프로필사진', -- 프로필사진
  token       VARCHAR(255) NULL     COMMENT '이메일토큰', -- 이메일토큰
  status      BOOLEAN      NOT NULL COMMENT '상태' -- 상태
)
COMMENT '회원';

-- 회원
ALTER TABLE member
  ADD CONSTRAINT PK_member -- 회원 기본키
    PRIMARY KEY (
      mno -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_member
  ON member ( -- 회원
    email ASC -- 이메일(아이디)
  );

-- 회원 유니크 인덱스2
CREATE UNIQUE INDEX UIX_member2
  ON member ( -- 회원
    tel ASC -- 연락처
  );

-- 회원 유니크 인덱스3
CREATE UNIQUE INDEX UIX_member3
  ON member ( -- 회원
    nick ASC -- 닉네임
  );

ALTER TABLE member
  MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 공모전
CREATE TABLE contest (
  ctstno  INTEGER      NOT NULL COMMENT '공모전번호', -- 공모전번호
  orgno   INTEGER      NOT NULL COMMENT '기관분류번호', -- 기관분류번호
  ctgno   INTEGER      NOT NULL COMMENT '공모전카테고리번호', -- 공모전카테고리번호
  title   VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  subcont MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  cdt     DATE         NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  sdate   DATE         NULL     COMMENT '시작일', -- 시작일
  edate   DATE         NULL     COMMENT '종료일', -- 종료일
  vw_cnt  INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  org     VARCHAR(255) NOT NULL COMMENT '주최기관명', -- 주최기관명
  appl    VARCHAR(255) NULL     COMMENT '접수방법', -- 접수방법
  cont    MEDIUMTEXT   NULL     COMMENT '상세내용', -- 상세내용
  page    VARCHAR(255) NULL     COMMENT '홈페이지', -- 홈페이지
  size    INTEGER      NULL     COMMENT '참가인원', -- 참가인원
  qual    VARCHAR(255) NULL     COMMENT '참가자격', -- 참가자격
  team    BOOLEAN      NOT NULL COMMENT '팀여부', -- 팀여부
  reward  INTEGER      NULL     COMMENT '상금' -- 상금
)
COMMENT '공모전';

-- 공모전
ALTER TABLE contest
  ADD CONSTRAINT PK_contest -- 공모전 기본키
    PRIMARY KEY (
      ctstno -- 공모전번호
    );

ALTER TABLE contest
  MODIFY COLUMN ctstno INTEGER NOT NULL AUTO_INCREMENT COMMENT '공모전번호';

-- 재능판매
CREATE TABLE product (
  pno           INTEGER      NOT NULL COMMENT '재능판매번호', -- 재능판매번호
  pcno          VARCHAR(255) NOT NULL COMMENT '재능판매카테고리번호', -- 재능판매카테고리번호
  mno           INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title         VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont          MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  price         INTEGER      NOT NULL COMMENT '가격', -- 가격
  vw_cnt        INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  cdt           DATE         NOT NULL DEFAULT now() COMMENT '작성일', -- 작성일
  thumbnail     VARCHAR(255) NULL     COMMENT '썸네일파일이름', -- 썸네일파일이름
  thumbnailpath VARCHAR(255) NULL     COMMENT '썸네일파일경로' -- 썸네일파일경로
)
COMMENT '재능판매';

-- 재능판매
ALTER TABLE product
  ADD CONSTRAINT PK_product -- 재능판매 기본키
    PRIMARY KEY (
      pno -- 재능판매번호
    );

ALTER TABLE product
  MODIFY COLUMN pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '재능판매번호';

-- 재능 판매 첨부파일
CREATE TABLE product_file (
  pfno  INTEGER      NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  fname VARCHAR(255) NOT NULL COMMENT '파일이름', -- 파일이름
  fpath VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  pno   INTEGER      NOT NULL COMMENT '재능판매번호' -- 재능판매번호
)
COMMENT '재능 판매 첨부파일';

-- 재능 판매 첨부파일
ALTER TABLE product_file
  ADD CONSTRAINT PK_product_file -- 재능 판매 첨부파일 기본키
    PRIMARY KEY (
      pfno -- 첨부파일번호
    );

ALTER TABLE product_file
  MODIFY COLUMN pfno INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

-- 공모전 첨부파일
CREATE TABLE contest_file (
  ctstfno INTEGER      NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  fname   VARCHAR(255) NOT NULL COMMENT '파일이름', -- 파일이름
  fpath   VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  ctstno  INTEGER      NOT NULL COMMENT '공모전번호' -- 공모전번호
)
COMMENT '공모전 첨부파일';

-- 공모전 첨부파일
ALTER TABLE contest_file
  ADD CONSTRAINT PK_contest_file -- 공모전 첨부파일 기본키
    PRIMARY KEY (
      ctstfno -- 첨부파일번호
    );

-- 공지사항
CREATE TABLE notice (
  ntcno  INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  title  VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont   MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  cdt    DATE         NOT NULL DEFAULT now() COMMENT '작성일', -- 작성일
  vw_cnt INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  fname  VARCHAR(255) NULL     COMMENT '파일이름', -- 파일이름
  fpath  VARCHAR(255) NULL     COMMENT '파일경로' -- 파일경로
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE notice
  ADD CONSTRAINT PK_notice -- 공지사항 기본키
    PRIMARY KEY (
      ntcno -- 게시글번호
    );

ALTER TABLE notice
  MODIFY COLUMN ntcno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 포트폴리오
CREATE TABLE portfolio (
  ptno  INTEGER      NOT NULL COMMENT '포트폴리오번호', -- 포트폴리오번호
  mno   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont  MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  cdt   DATE         NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '포트폴리오';

-- 포트폴리오
ALTER TABLE portfolio
  ADD CONSTRAINT PK_portfolio -- 포트폴리오 기본키
    PRIMARY KEY (
      ptno -- 포트폴리오번호
    );

ALTER TABLE portfolio
  MODIFY COLUMN ptno INTEGER NOT NULL AUTO_INCREMENT COMMENT '포트폴리오번호';

-- 재능판매카테고리
CREATE TABLE product_category (
  pcno     VARCHAR(255) NOT NULL COMMENT '재능판매카테고리번호', -- 재능판매카테고리번호
  pctier   INTEGER      NOT NULL COMMENT '카테고리티어', -- 카테고리티어
  pcname   VARCHAR(255) NOT NULL COMMENT '카테고리이름', -- 카테고리이름
  pcparent VARCHAR(255) NULL     COMMENT '부모카테고리번호' -- 부모카테고리번호
)
COMMENT '재능판매카테고리';

-- 재능판매카테고리
ALTER TABLE product_category
  ADD CONSTRAINT PK_product_category -- 재능판매카테고리 기본키
    PRIMARY KEY (
      pcno -- 재능판매카테고리번호
    );

-- 재능판매카테고리 유니크 인덱스
CREATE UNIQUE INDEX UIX_product_category
  ON product_category ( -- 재능판매카테고리
    pcname ASC -- 카테고리이름
  );

-- 1 : 1 문의
CREATE TABLE qna (
  qnano     INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  qnacno    INTEGER      NOT NULL COMMENT '카테고리 번호', -- 카테고리 번호
  title     VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont      MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  cdt       DATE         NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  mno       INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  answer    MEDIUMTEXT   NULL     COMMENT '답변내용', -- 답변내용
  answercdt DATE         NULL     COMMENT '답변일시' -- 답변일시
)
COMMENT '1 : 1 문의';

-- 1 : 1 문의
ALTER TABLE qna
  ADD CONSTRAINT PK_qna -- 1 : 1 문의 기본키
    PRIMARY KEY (
      qnano -- 게시글번호
    );

ALTER TABLE qna
  MODIFY COLUMN qnano INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 문의카테고리
CREATE TABLE qna_category (
  qnacno INTEGER      NOT NULL COMMENT '카테고리 번호', -- 카테고리 번호
  name   VARCHAR(255) NOT NULL COMMENT '카테고리명' -- 카테고리명
)
COMMENT '문의카테고리';

-- 문의카테고리
ALTER TABLE qna_category
  ADD CONSTRAINT PK_qna_category -- 문의카테고리 기본키
    PRIMARY KEY (
      qnacno -- 카테고리 번호
    );

-- 문의카테고리 유니크 인덱스
CREATE UNIQUE INDEX UIX_qna_category
  ON qna_category ( -- 문의카테고리
    name ASC -- 카테고리명
  );

-- 후기 게시판
CREATE TABLE product_review (
  prno  INTEGER      NOT NULL COMMENT '후기글번호', -- 후기글번호
  mno   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  pno   INTEGER      NOT NULL COMMENT '재능판매번호', -- 재능판매번호
  title VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont  MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  cdt   DATE         NOT NULL DEFAULT now() COMMENT '작성일', -- 작성일
  scope INTEGER      NOT NULL COMMENT '별점' -- 별점
)
COMMENT '후기 게시판';

-- 후기 게시판
ALTER TABLE product_review
  ADD CONSTRAINT PK_product_review -- 후기 게시판 기본키
    PRIMARY KEY (
      prno -- 후기글번호
    );

ALTER TABLE product_review
  MODIFY COLUMN prno INTEGER NOT NULL AUTO_INCREMENT COMMENT '후기글번호';

-- 후기 첨부파일
CREATE TABLE product_review_file (
  prfno INTEGER      NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  fname VARCHAR(255) NOT NULL COMMENT '파일이름', -- 파일이름
  fpath VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  prno  INTEGER      NOT NULL COMMENT '후기글번호' -- 후기글번호
)
COMMENT '후기 첨부파일';

-- 후기 첨부파일
ALTER TABLE product_review_file
  ADD CONSTRAINT PK_product_review_file -- 후기 첨부파일 기본키
    PRIMARY KEY (
      prfno -- 첨부파일번호
    );

ALTER TABLE product_review_file
  MODIFY COLUMN prfno INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

-- 공모전 카테고리
CREATE TABLE contest_category (
  ctgno INTEGER      NOT NULL COMMENT '공모전카테고리번호', -- 공모전카테고리번호
  name  VARCHAR(255) NOT NULL COMMENT '공모전카테고리명' -- 공모전카테고리명
)
COMMENT '공모전 카테고리';

-- 공모전 카테고리
ALTER TABLE contest_category
  ADD CONSTRAINT PK_contest_category -- 공모전 카테고리 기본키
    PRIMARY KEY (
      ctgno -- 공모전카테고리번호
    );

-- 공모전 카테고리 유니크 인덱스
CREATE UNIQUE INDEX UIX_contest_category
  ON contest_category ( -- 공모전 카테고리
    name ASC -- 공모전카테고리명
  );

-- 포트폴리오 첨부파일
CREATE TABLE portfolio_file (
  ptfno INTEGER      NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  fname VARCHAR(255) NOT NULL COMMENT '파일이름', -- 파일이름
  fpath VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  ptno  INTEGER      NOT NULL COMMENT '포트폴리오번호' -- 포트폴리오번호
)
COMMENT '포트폴리오 첨부파일';

-- 포트폴리오 첨부파일
ALTER TABLE portfolio_file
  ADD CONSTRAINT PK_portfolio_file -- 포트폴리오 첨부파일 기본키
    PRIMARY KEY (
      ptfno -- 첨부파일번호
    );

ALTER TABLE portfolio_file
  MODIFY COLUMN ptfno INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

-- 1 : 1 문의 첨부파일
CREATE TABLE qna_file (
  qnafno INTEGER      NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  fname  VARCHAR(255) NOT NULL COMMENT '파일이름', -- 파일이름
  fpath  VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  qnano  INTEGER      NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '1 : 1 문의 첨부파일';

-- 1 : 1 문의 첨부파일
ALTER TABLE qna_file
  ADD CONSTRAINT PK_qna_file -- 1 : 1 문의 첨부파일 기본키
    PRIMARY KEY (
      qnafno -- 첨부파일번호
    );

ALTER TABLE qna_file
  MODIFY COLUMN qnafno INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

-- 주최기관
CREATE TABLE contest_organization (
  orgno INTEGER      NOT NULL COMMENT '기관분류번호', -- 기관분류번호
  name  VARCHAR(255) NOT NULL COMMENT '기관분류명' -- 기관분류명
)
COMMENT '주최기관';

-- 주최기관
ALTER TABLE contest_organization
  ADD CONSTRAINT PK_contest_organization -- 주최기관 기본키
    PRIMARY KEY (
      orgno -- 기관분류번호
    );

-- 주최기관 유니크 인덱스
CREATE UNIQUE INDEX UIX_contest_organization
  ON contest_organization ( -- 주최기관
    name ASC -- 기관분류명
  );

-- 위시리스트
CREATE TABLE wishlist (
  mno INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  pno INTEGER NOT NULL COMMENT '재능판매번호' -- 재능판매번호
)
COMMENT '위시리스트';

-- 위시리스트
ALTER TABLE wishlist
  ADD CONSTRAINT PK_wishlist -- 위시리스트 기본키
    PRIMARY KEY (
      mno, -- 회원번호
      pno  -- 재능판매번호
    );

-- 팀원모집
CREATE TABLE team (
  tno    INTEGER    NOT NULL COMMENT '팀원모집번호', -- 팀원모집번호
  ctstno INTEGER    NOT NULL COMMENT '공모전번호', -- 공모전번호
  mno    INTEGER    NOT NULL COMMENT '회원번호', -- 회원번호
  cont   MEDIUMTEXT NOT NULL COMMENT '소개글', -- 소개글
  cdt    DATE       NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '팀원모집';

-- 팀원모집
ALTER TABLE team
  ADD CONSTRAINT PK_team -- 팀원모집 기본키
    PRIMARY KEY (
      tno -- 팀원모집번호
    );

ALTER TABLE team
  MODIFY COLUMN tno INTEGER NOT NULL AUTO_INCREMENT COMMENT '팀원모집번호';

-- 팀원모집분야지원
CREATE TABLE team_field_member (
  tfmno  INTEGER      NOT NULL COMMENT '팀원모집분야지원번호', -- 팀원모집분야지원번호
  tfno   INTEGER      NOT NULL COMMENT '모집분야번호', -- 모집분야번호
  mno    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  cont   MEDIUMTEXT   NOT NULL COMMENT '자기소개', -- 자기소개
  cdt    DATE         NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  type   BOOLEAN      NOT NULL COMMENT '상태', -- 상태
  fpath1 VARCHAR(255) NULL     COMMENT '포트폴리오1', -- 포트폴리오1
  fpath2 VARCHAR(255) NULL     COMMENT '포트폴리오2', -- 포트폴리오2
  fpath3 VARCHAR(255) NULL     COMMENT '포트폴리오3' -- 포트폴리오3
)
COMMENT '팀원모집분야지원';

-- 팀원모집분야지원
ALTER TABLE team_field_member
  ADD CONSTRAINT PK_team_field_member -- 팀원모집분야지원 기본키
    PRIMARY KEY (
      tfmno -- 팀원모집분야지원번호
    );

ALTER TABLE team_field_member
  MODIFY COLUMN tfmno INTEGER NOT NULL AUTO_INCREMENT COMMENT '팀원모집분야지원번호';

-- 모집분야
CREATE TABLE team_field (
  tfno INTEGER      NOT NULL COMMENT '모집분야번호', -- 모집분야번호
  tno  INTEGER      NOT NULL COMMENT '팀원모집번호', -- 팀원모집번호
  name VARCHAR(255) NOT NULL COMMENT '모집분야', -- 모집분야
  size INTEGER      NOT NULL COMMENT '인원', -- 인원
  type BOOLEAN      NOT NULL COMMENT '상태' -- 상태
)
COMMENT '모집분야';

-- 모집분야
ALTER TABLE team_field
  ADD CONSTRAINT PK_team_field -- 모집분야 기본키
    PRIMARY KEY (
      tfno -- 모집분야번호
    );

ALTER TABLE team_field
  MODIFY COLUMN tfno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모집분야번호';

-- FAQ
CREATE TABLE faq (
  faqno INTEGER      NOT NULL COMMENT 'FAQ번호', -- FAQ번호
  title VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont  MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  type  VARCHAR(50)  NOT NULL COMMENT '유저타입' -- 유저타입
)
COMMENT 'FAQ';

-- FAQ
ALTER TABLE faq
  ADD CONSTRAINT PK_faq -- FAQ 기본키
    PRIMARY KEY (
      faqno -- FAQ번호
    );

ALTER TABLE faq
  MODIFY COLUMN faqno INTEGER NOT NULL AUTO_INCREMENT COMMENT 'FAQ번호';

-- 관심사
CREATE TABLE interest (
  mno  INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  pcno VARCHAR(255) NOT NULL COMMENT '재능판매카테고리번호' -- 재능판매카테고리번호
)
COMMENT '관심사';

-- 관심사
ALTER TABLE interest
  ADD CONSTRAINT PK_interest -- 관심사 기본키
    PRIMARY KEY (
      mno,  -- 회원번호
      pcno  -- 재능판매카테고리번호
    );

-- 관리자
CREATE TABLE admin_member (
  amno INTEGER      NOT NULL COMMENT '관리자번호', -- 관리자번호
  id   VARCHAR(50)  NOT NULL COMMENT '아이디', -- 아이디
  pwd  VARCHAR(100) NOT NULL COMMENT '비밀번호' -- 비밀번호
)
COMMENT '관리자';

-- 관리자
ALTER TABLE admin_member
  ADD CONSTRAINT PK_admin_member -- 관리자 기본키
    PRIMARY KEY (
      amno -- 관리자번호
    );

-- 관리자 유니크 인덱스
CREATE UNIQUE INDEX UIX_admin_member
  ON admin_member ( -- 관리자
    id ASC -- 아이디
  );

ALTER TABLE admin_member
  MODIFY COLUMN amno INTEGER NOT NULL AUTO_INCREMENT COMMENT '관리자번호';

-- 주문내역
CREATE TABLE product_order (
  pono    INTEGER      NOT NULL COMMENT '구매번호', -- 구매번호
  mno     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  pno     INTEGER      NOT NULL COMMENT '재능판매번호', -- 재능판매번호
  cdt     DATE         NOT NULL DEFAULT now() COMMENT '주문날짜', -- 주문날짜
  payment VARCHAR(255) NOT NULL COMMENT '결제수단', -- 결제수단
  paycdt  DATE         NOT NULL COMMENT '결제날짜', -- 결제날짜
  status  VARCHAR(255) NOT NULL COMMENT '진행상태' -- 진행상태
)
COMMENT '주문내역';

-- 주문내역
ALTER TABLE product_order
  ADD CONSTRAINT PK_product_order -- 주문내역 기본키
    PRIMARY KEY (
      pono -- 구매번호
    );

ALTER TABLE product_order
  MODIFY COLUMN pono INTEGER NOT NULL AUTO_INCREMENT COMMENT '구매번호';

-- 1대1대화
CREATE TABLE message (
  msgno INTEGER    NOT NULL COMMENT '메신저번호', -- 메신저번호
  mno   INTEGER    NOT NULL COMMENT '질문자번호', -- 질문자번호
  mno2  INTEGER    NOT NULL COMMENT '판매자번호', -- 판매자번호
  cont  MEDIUMTEXT NOT NULL COMMENT '메시지내용', -- 메시지내용
  type  BOOLEAN    NOT NULL COMMENT '메시지유형', -- 메시지유형
  cdt   DATE       NOT NULL DEFAULT now() COMMENT '생성날짜' -- 생성날짜
)
COMMENT '1대1대화';

-- 1대1대화
ALTER TABLE message
  ADD CONSTRAINT PK_message -- 1대1대화 기본키
    PRIMARY KEY (
      msgno -- 메신저번호
    );

ALTER TABLE message
  MODIFY COLUMN msgno INTEGER NOT NULL AUTO_INCREMENT COMMENT '메신저번호';

-- 직업
CREATE TABLE job (
  jno  INTEGER      NOT NULL COMMENT '직업번호', -- 직업번호
  name VARCHAR(255) NOT NULL COMMENT '직업명' -- 직업명
)
COMMENT '직업';

-- 직업
ALTER TABLE job
  ADD CONSTRAINT PK_job -- 직업 기본키
    PRIMARY KEY (
      jno -- 직업번호
    );

-- 직업 유니크 인덱스
CREATE UNIQUE INDEX UIX_job
  ON job ( -- 직업
    name ASC -- 직업명
  );

ALTER TABLE job
  MODIFY COLUMN jno INTEGER NOT NULL AUTO_INCREMENT COMMENT '직업번호';

-- 1대1대화 첨부파일
CREATE TABLE message_file (
  msgfno INTEGER      NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  fname  VARCHAR(255) NOT NULL COMMENT '파일이름', -- 파일이름
  fpath  VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  msgno  INTEGER      NOT NULL COMMENT '메신저번호' -- 메신저번호
)
COMMENT '1대1대화 첨부파일';

-- 1대1대화 첨부파일
ALTER TABLE message_file
  ADD CONSTRAINT PK_message_file -- 1대1대화 첨부파일 기본키
    PRIMARY KEY (
      msgfno -- 첨부파일번호
    );

ALTER TABLE message_file
  MODIFY COLUMN msgfno INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

-- 회원
ALTER TABLE member
  ADD CONSTRAINT FK_job_TO_member -- 직업 -> 회원
    FOREIGN KEY (
      jno -- 직업번호
    )
    REFERENCES job ( -- 직업
      jno -- 직업번호
    );

-- 공모전
ALTER TABLE contest
  ADD CONSTRAINT FK_contest_category_TO_contest -- 공모전 카테고리 -> 공모전
    FOREIGN KEY (
      ctgno -- 공모전카테고리번호
    )
    REFERENCES contest_category ( -- 공모전 카테고리
      ctgno -- 공모전카테고리번호
    );

-- 공모전
ALTER TABLE contest
  ADD CONSTRAINT FK_contest_organization_TO_contest -- 주최기관 -> 공모전
    FOREIGN KEY (
      orgno -- 기관분류번호
    )
    REFERENCES contest_organization ( -- 주최기관
      orgno -- 기관분류번호
    );

-- 재능판매
ALTER TABLE product
  ADD CONSTRAINT FK_product_category_TO_product -- 재능판매카테고리 -> 재능판매
    FOREIGN KEY (
      pcno -- 재능판매카테고리번호
    )
    REFERENCES product_category ( -- 재능판매카테고리
      pcno -- 재능판매카테고리번호
    );

-- 재능판매
ALTER TABLE product
  ADD CONSTRAINT FK_member_TO_product -- 회원 -> 재능판매
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 재능 판매 첨부파일
ALTER TABLE product_file
  ADD CONSTRAINT FK_product_TO_product_file -- 재능판매 -> 재능 판매 첨부파일
    FOREIGN KEY (
      pno -- 재능판매번호
    )
    REFERENCES product ( -- 재능판매
      pno -- 재능판매번호
    );

-- 공모전 첨부파일
ALTER TABLE contest_file
  ADD CONSTRAINT FK_contest_TO_contest_file -- 공모전 -> 공모전 첨부파일
    FOREIGN KEY (
      ctstno -- 공모전번호
    )
    REFERENCES contest ( -- 공모전
      ctstno -- 공모전번호
    );

-- 포트폴리오
ALTER TABLE portfolio
  ADD CONSTRAINT FK_member_TO_portfolio -- 회원 -> 포트폴리오
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 재능판매카테고리
ALTER TABLE product_category
  ADD CONSTRAINT FK_product_category_TO_product_category -- 재능판매카테고리 -> 재능판매카테고리
    FOREIGN KEY (
      pcparent -- 부모카테고리번호
    )
    REFERENCES product_category ( -- 재능판매카테고리
      pcno -- 재능판매카테고리번호
    );

-- 1 : 1 문의
ALTER TABLE qna
  ADD CONSTRAINT FK_member_TO_qna -- 회원 -> 1 : 1 문의
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 1 : 1 문의
ALTER TABLE qna
  ADD CONSTRAINT FK_qna_category_TO_qna -- 문의카테고리 -> 1 : 1 문의
    FOREIGN KEY (
      qnacno -- 카테고리 번호
    )
    REFERENCES qna_category ( -- 문의카테고리
      qnacno -- 카테고리 번호
    );

-- 후기 게시판
ALTER TABLE product_review
  ADD CONSTRAINT FK_member_TO_product_review -- 회원 -> 후기 게시판
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 후기 게시판
ALTER TABLE product_review
  ADD CONSTRAINT FK_product_TO_product_review -- 재능판매 -> 후기 게시판
    FOREIGN KEY (
      pno -- 재능판매번호
    )
    REFERENCES product ( -- 재능판매
      pno -- 재능판매번호
    );

-- 후기 첨부파일
ALTER TABLE product_review_file
  ADD CONSTRAINT FK_product_review_TO_product_review_file -- 후기 게시판 -> 후기 첨부파일
    FOREIGN KEY (
      prno -- 후기글번호
    )
    REFERENCES product_review ( -- 후기 게시판
      prno -- 후기글번호
    );

-- 포트폴리오 첨부파일
ALTER TABLE portfolio_file
  ADD CONSTRAINT FK_portfolio_TO_portfolio_file -- 포트폴리오 -> 포트폴리오 첨부파일
    FOREIGN KEY (
      ptno -- 포트폴리오번호
    )
    REFERENCES portfolio ( -- 포트폴리오
      ptno -- 포트폴리오번호
    );

-- 1 : 1 문의 첨부파일
ALTER TABLE qna_file
  ADD CONSTRAINT FK_qna_TO_qna_file -- 1 : 1 문의 -> 1 : 1 문의 첨부파일
    FOREIGN KEY (
      qnano -- 게시글번호
    )
    REFERENCES qna ( -- 1 : 1 문의
      qnano -- 게시글번호
    );

-- 위시리스트
ALTER TABLE wishlist
  ADD CONSTRAINT FK_member_TO_wishlist -- 회원 -> 위시리스트
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 위시리스트
ALTER TABLE wishlist
  ADD CONSTRAINT FK_product_TO_wishlist -- 재능판매 -> 위시리스트
    FOREIGN KEY (
      pno -- 재능판매번호
    )
    REFERENCES product ( -- 재능판매
      pno -- 재능판매번호
    );

-- 팀원모집
ALTER TABLE team
  ADD CONSTRAINT FK_contest_TO_team -- 공모전 -> 팀원모집
    FOREIGN KEY (
      ctstno -- 공모전번호
    )
    REFERENCES contest ( -- 공모전
      ctstno -- 공모전번호
    );

-- 팀원모집
ALTER TABLE team
  ADD CONSTRAINT FK_member_TO_team -- 회원 -> 팀원모집
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 팀원모집분야지원
ALTER TABLE team_field_member
  ADD CONSTRAINT FK_team_field_TO_team_field_member -- 모집분야 -> 팀원모집분야지원
    FOREIGN KEY (
      tfno -- 모집분야번호
    )
    REFERENCES team_field ( -- 모집분야
      tfno -- 모집분야번호
    );

-- 팀원모집분야지원
ALTER TABLE team_field_member
  ADD CONSTRAINT FK_member_TO_team_field_member -- 회원 -> 팀원모집분야지원
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 모집분야
ALTER TABLE team_field
  ADD CONSTRAINT FK_team_TO_team_field -- 팀원모집 -> 모집분야
    FOREIGN KEY (
      tno -- 팀원모집번호
    )
    REFERENCES team ( -- 팀원모집
      tno -- 팀원모집번호
    );

-- 관심사
ALTER TABLE interest
  ADD CONSTRAINT FK_member_TO_interest -- 회원 -> 관심사
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 관심사
ALTER TABLE interest
  ADD CONSTRAINT FK_product_category_TO_interest -- 재능판매카테고리 -> 관심사
    FOREIGN KEY (
      pcno -- 재능판매카테고리번호
    )
    REFERENCES product_category ( -- 재능판매카테고리
      pcno -- 재능판매카테고리번호
    );

-- 주문내역
ALTER TABLE product_order
  ADD CONSTRAINT FK_member_TO_product_order -- 회원 -> 주문내역
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 주문내역
ALTER TABLE product_order
  ADD CONSTRAINT FK_product_TO_product_order -- 재능판매 -> 주문내역
    FOREIGN KEY (
      pno -- 재능판매번호
    )
    REFERENCES product ( -- 재능판매
      pno -- 재능판매번호
    );

-- 1대1대화
ALTER TABLE message
  ADD CONSTRAINT FK_member_TO_message -- 회원 -> 1대1대화
    FOREIGN KEY (
      mno -- 질문자번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 1대1대화
ALTER TABLE message
  ADD CONSTRAINT FK_member_TO_message2 -- 회원 -> 1대1대화2
    FOREIGN KEY (
      mno2 -- 판매자번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 1대1대화 첨부파일
ALTER TABLE message_file
  ADD CONSTRAINT FK_message_TO_message_file -- 1대1대화 -> 1대1대화 첨부파일
    FOREIGN KEY (
      msgno -- 메신저번호
    )
    REFERENCES message ( -- 1대1대화
      msgno -- 메신저번호
    );