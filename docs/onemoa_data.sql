-- 데이터베이스 생성
-- create database onemoa character set utf8 collate utf8_general_ci;
-- create database test1 character set utf8 collate utf8_general_ci;
-- 사용자 생성 후 데이터베이스에 권한 설정
-- create user 'study'@'localhost' identified by '1111';
-- grant all on onemoa.* to 'study'@'localhost';

-- csv 파일 업로드
-- load data local infile '/Users/bebeco/bitcamp-project/DB/파일명.csv' into table [DB명].[테이블명]] fields terminated by ',';

-- UPDATE [테이블] SET [열] = '변경할값' WHERE [조건] 
-- update job set name='직장인' where jno=2;

-- 테이블 넣는 순서
-- 직업번호 > 회원
-- 재능판매카테고리 > 재능판매 > 재능판매 첨부파일 > 주문내역 > 관심사

-- 직업번호 테이블
insert into job (jno, name) values (1, '프리랜서');
insert into job (jno, name) values (2, '직장인');
insert into job (jno, name) values (3, '소상공인');
insert into job (jno, name) values (4, '스타트업 창업자');
insert into job (jno, name) values (5, '대학(원)생');
insert into job (jno, name) values (6, '취업준비생');

-- 회원 테이블
insert into member (mno, email, pwd, nick, name, tel, cdt, pstno, base_addr, detail_addr, jno, bank, account, profile, token, status) values (1, 'user1@test.com', sha2('1111',256), '판매자닉네임
', '판매자', '01011112222', '2022-10-07', '06134', '서울특별시 강남구 역삼동 819-3', '삼오빌딩 5-9층', 1, '국민은행', '111111-11-111111', '프로필사진파일경로', '이메일토큰', 1);
insert into member (mno, email, pwd, nick, name, tel, cdt, pstno, base_addr, detail_addr, jno, bank, account, profile, token, status) values (2, 'user2@test.com', sha2('1111',256), '회원닉네임1', '홍길동', '01022222222', '2022-10-07', '06134', '서울특별시 강남구 역삼동 819-3', '삼오빌딩 5-9층', 2, null, null, '프로필사진파일경로', '이메일토큰', 1);
insert into member (mno, email, pwd, nick, name, tel, cdt, pstno, base_addr, detail_addr, jno, bank, account, profile, token, status) values (3, 'user3@test.com', sha2('1111',256), '회원닉네임2', '임꺽정', '01033332222', '2022-10-07', '06134', '서울특별시 강남구 역삼동 819-3', '삼오빌딩 5-9층', 3, null, null, '프로필사진파일경로', '이메일토큰', 1);
insert into member (mno, email, pwd, nick, name, tel, cdt, pstno, base_addr, detail_addr, jno, bank, account, profile, token, status) values (4, 'user4@test.com', sha2('1111',256), '회원닉네임3', '유관순', '01044442222', '2022-10-07', '06134', '서울특별시 강남구 역삼동 819-3', '삼오빌딩 5-9층', 4, null, null, '프로필사진파일경로', '이메일토큰', 1);
insert into member (mno, email, pwd, nick, name, tel, cdt, pstno, base_addr, detail_addr, jno, bank, account, profile, token, status) values (5, 'user5@test.com', sha2('1111',256), '회원닉네임4', '홍길동1', '01055552222', '2022-10-07', '06134', '서울특별시 강남구 역삼동 819-3', '삼오빌딩 5-9층', 5, null, null, '프로필사진파일경로', '이메일토큰', 1);
insert into member (mno, email, pwd, nick, name, tel, cdt, pstno, base_addr, detail_addr, jno, bank, account, profile, token, status) values (6, 'user6@test.com', sha2('1111',256), '회원닉네임5', '임꺽정2', '01066662222', '2022-10-07', '06134', '서울특별시 강남구 역삼동 819-3', '삼오빌딩 5-9층', 6, null, null, '프로필사진파일경로', '이메일토큰', 1);
insert into member (mno, email, pwd, nick, name, tel, cdt, pstno, base_addr, detail_addr, jno, bank, account, profile, token, status) values (7, 'user7@test.com', sha2('1111',256), '회원닉네임6', '유관순2', '01076662222', '2022-10-07', '06134', '서울특별시 강남구 역삼동 819-3', '삼오빌딩 5-9층', 6, null, null, '프로필사진파일경로', '이메일토큰', 1);


-- 재능판매카테고리 테이블
insert into product_category (pcno, pctier, pcname, pcparent) values ('010000', 1, '디자인', null);
insert into product_category (pcno, pctier, pcname, pcparent) values ('020000', 1, 'IT·프로그래밍', null);
insert into product_category (pcno, pctier, pcname, pcparent) values ('030000', 1, '영상·사진·음향', null);
insert into product_category (pcno, pctier, pcname, pcparent) values ('040000', 1, '마케팅', null);
insert into product_category (pcno, pctier, pcname, pcparent) values ('050000', 1, '번역·통역', null);
insert into product_category (pcno, pctier, pcname, pcparent) values ('060000', 1, '문서·글쓰기', null);
insert into product_category (pcno, pctier, pcname, pcparent) values ('070000', 1, '비즈니스컨설팅', null);
insert into product_category (pcno, pctier, pcname, pcparent) values ('080000', 1, '주문제작', null);
insert into product_category (pcno, pctier, pcname, pcparent) values ('010100', 2, '로고·브랜딩', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('010200', 2, '상세·이벤트 페이지', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('010300', 2, '명함·인쇄·홍보물', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('010400', 2, 'PPT·인포그래픽', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('010500', 2, '웹·모바일 디자인', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('010600', 2, '패키지 디자인', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('010700', 2, '일러스트·캐리커쳐', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('010800', 2, '웹툰·캐릭터·이모티콘', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('010900', 2, '블로그·SNS·썸네일', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011000', 2, '포토샵·파일변환', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011100', 2, '제품·3D프린팅', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011200', 2, '공간디자인', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011300', 2, '북·앨범디자인', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011400', 2, '캘리그라피·폰트', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011500', 2, '메타버스·NFT아트', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011600', 2, 'VR·AR·게임', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011700', 2, '의류디자인', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011800', 2, '간판·시공', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('011900', 2, '템플릿', '010000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020100', 2, '모바일 앱', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020200', 2, '웹', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020300', 2, '프로그램', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020400', 2, '쇼핑몰·커머스', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020500', 2, '워드프레스', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020600', 2, '데이터', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020700', 2, '기획', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020800', 2, '게임·AR·VR', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('020900', 2, '메타버스', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('021000', 2, '블록체인', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('021100', 2, '임베디드 HW·SW', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('021200', 2, '서버·호스팅', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('021300', 2, '데이터베이스', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('021400', 2, '보안', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('021500', 2, '챗봇', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('021600', 2, 'QA·테스트', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('021700', 2, '기술지원', '020000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030100', 2, '영상촬영·편집', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030200', 2, '인터뷰 영상', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030300', 2, '교육 영상', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030400', 2, '이벤트 영상', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030500', 2, '유튜브 제작', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030600', 2, '온라인 생중계', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030700', 2, '드론 촬영', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030800', 2, '애니메이션', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('030900', 2, '인포·모션그래픽', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031000', 2, '3D·VR', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031100', 2, '인트로·로고', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031200', 2, '영상 자막', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031300', 2, '영상 템플릿', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031400', 2, '사진촬영', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031500', 2, '사진·영상 보정', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031600', 2, '성우', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031700', 2, '음악·사운드', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031800', 2, '모델·MC·공연', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('031900', 2, '스튜디오 렌탈', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('032000', 2, '헤어메이크업', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('032100', 2, '콘티·스토리보드', '030000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('040100', 2, '블로그 마케팅', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('040200', 2, '카페 마케팅', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('040300', 2, 'SNS 마케팅', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('040400', 2, '쇼핑몰·스토어', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('040500', 2, '체험단·기자단 대행', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('040600', 2, '웹 트래픽', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('040700', 2, '언론홍보', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('040800', 2, '종합광고대행', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041000', 2, '지도등록·홍보', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041100', 2, '앱마케팅', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041200', 2, '라이브커머스', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041300', 2, '개인 인플루언서', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041400', 2, '키워드·배너광고', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041500', 2, '포털질문·답변', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041600', 2, '마케팅 컨설팅', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041700', 2, '영상 마케팅', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041800', 2, '오프라인광고', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('041900', 2, '해외마케팅', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('042000', 2, '브랜드마케팅', '040000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('050100', 2, '산업별 전문번역', '050000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('050200', 2, '일반 번역', '050000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('050300', 2, '통역', '050000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('050400', 2, '영상번역', '050000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('050500', 2, '감수', '050000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('050600', 2, '번역공증대행', '050000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060100', 2, '기업명·네이밍', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060200', 2, '제품 카피라이팅', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060300', 2, '광고 카피라이팅', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060400', 2, 'SNS 카피라이팅', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060500', 2, 'Email 카피라이팅', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060600', 2, 'UX 라이팅', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060700', 2, '블로그 원고', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060800', 2, '웹사이트·SEO', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('060900', 2, '보도자료·기사·칼럼', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061000', 2, '산업별 전문 글작성', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061100', 2, '타이핑(영상)', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061200', 2, '타이핑(문서)', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061300', 2, '책·전자책 출판', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061400', 2, '시나리오·대본', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061500', 2, '논문', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061600', 2, '교정·교열 첨삭', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061700', 2, '잡포털 이력서 교정', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('061800', 2, '맞춤양식', '060000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070100', 2, '사업계획서·투자제안서', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070200', 2, '퍼스널 브랜딩', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070300', 2, '유튜브 컨설팅', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070400', 2, '창업 컨설팅', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070500', 2, '쇼핑몰·스토어 창업', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070600', 2, '크라우드펀딩', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070700', 2, '해외사업 컨설팅', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070800', 2, '리서치·설문조사', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('070900', 2, 'HR 컨설팅', '070000');
insert into product_category (pcno, pctier, pcname, pcparent) values ('071000', 2, '업무지원·CS', '070000');

-- 재능판매 테이블
INSERT INTO `product` (`pno`, `pcno`, `mno`, `title`, `cont`, `price`, `vw_cnt`, `cdt`, `thumbnail`, `thumbnailpath`)
VALUES
  (1, '010100', 1, '로고제작 공모전 488회우승 로고디자인 CI BI 드림', ' 로고 디자인 공모전 488회 우승 국내 로고 제작 경력 최다 디자이너가 직접 진행 상담은 무료입니다.:)', 1000000, 1000, '2022-10-06', '썸네일파일이름.png', '3b0f725d-8c53-4cae-9eec-58e64a50316c\n'),
  (2, '010100', 1, '미술,디자인,작가가 모여 감각적인 로고 제작해드립니다.', '안녕하세요:)\n디자인, 미술, 포토그래퍼가 모인 로고회사입니다.\n다양한 분야에서 다양한 시선으로 고객님을 이해하며 소통하는 로고고입니다.\n브랜드의 첫인상인 로고 끝까지 책임지고 만들어드리겠습니다.', 220000, 1000, '2022-10-06', '썸네일파일이름.png', 'b1fe7835-1fcd-40d6-b495-450e5cac3f21'),
  (3, '010200', 1, '고객의 마음을 사로잡는 상세페이지를 제작해 드립니다.\n', '-배너 / 이벤트페이지 / 쇼핑몰상세페이지\n-썸네일/자사물 상세페이지\n-쇼핑몰 상세페이지\n-오픈마켓 상세페이지\n\n무엇이든 디자인하고 책임지고 작업해드리겠습니다.!!', 275000, 1000, '2022-10-06', '썸네일파일이름.png', '5f769e8c-378c-4044-9da4-108c0ce9a360'),
  (4, '010100', 1, '\"2년 연속 크몽 어워즈 대상\" 실력을 보여 드립니다.\n', '2020크몽 어워즈 대상 수상!\n2020~2021년 기준, 크몽 디자인 카테고리 압도적 1위!\n\n압도적인 만족도와 리뷰를 살펴보세요.', 300000, 1000, '2022-10-06', '썸네일파일이름.png', 'efc680ad-1f3f-47aa-a9fe-a84fcc740a2f'),
  (5, '010100', 1, '\"디자인어워드 수상\"검증된 디자이너가 로고디자인 제작해 드립니다.\n', '안녕하세요 Brand03입니다.\n직접 의뢰자님의 브랜드를 분석, 컨설팅하여 원하시는 컨셉에 맞게 고퀄리티 로고를 제작해드립니다. 디자이너 브랜드, 인테리어 전문 회사, 기업, 카페 브랜딩 등 다양한 경험으로 만족스러운 결과물, 합리적인 가격을 약속드리겠습니다.\n\n감각적인 로고(BI, CI) 디자인으로 의뢰자님의 시작을 함께 하겠습니다\n\n포트폴리오를 확인해 주세요 :D', 210000, 1000, '2022-10-06', '썸네일파일이름.png', '50462bb8-e3bf-44d3-a404-00a89667c20a'),
  (6, '010100', 1, '\"로고마스터\"가 당신과 브랜드의 가치에 한끗을 더하다\n', '브랜드(BRAND)는 당신의 비즈니스에 있어서 가장 중요하고 강력한 투자입니다.\n\n지난 10년간 국민은행, SH공사, 현대건설, 한국미니스톱, 기상청 등을 대상으로 다양한 디자인 프로젝트를 진행하였습니다', 79000, 1000, '2022-10-06', '썸네일파일이름.png', 'd829d0b0-4d68-4eec-a050-daa4982f2baf'),
  (7, '010100', 1, '컬러리스트산업기사,한양대출신디자이너가제작해 드립니다.\n', '한양여대 도자기공예과 졸업\n한양대 산업디자인 졸업\n컬러리스트 산업기사 자격증 취득\n오랜경력의 디자인업체 근무\n현재 포토샵,일러스트의 디자인관련 교육중', 175000, 2000, '2022-10-08', '썸네일파일이름.png', '7355f53e-b89d-4847-b64f-08d9d1edfd95\n'),
  (8, '010100', 1, '로고디자인 단 하루 24시간내 로고제작 해드립니다\n', '로고 전문 디자인 밴페로디자인 입니다.\n\n', 130000, 2000, '2022-10-08', '썸네일파일이름.png', '7355f53e-b89d-4847-b64f-08d9d1edfd95'),
  (9, '010100', 1, '로고제작 공모전 488회우승 로고디자인 CI BI 드림', ' 로고 디자인 공모전 488회 우승 국내 로고 제작 경력 최다 디자이너가 직접 진행 상담은 무료입니다.:)', 1000000, 1000, '2022-10-06', '썸네일파일이름.png', '3b0f725d-8c53-4cae-9eec-58e64a50316c\n'),
  (10, '010100', 1, '미술,디자인,작가가 모여 감각적인 로고 제작해드립니다.', '안녕하세요:)\n디자인, 미술, 포토그래퍼가 모인 로고회사입니다.\n다양한 분야에서 다양한 시선으로 고객님을 이해하며 소통하는 로고고입니다.\n브랜드의 첫인상인 로고 끝까지 책임지고 만들어드리겠습니다.', 220000, 1000, '2022-10-06', '썸네일파일이름.png', 'b1fe7835-1fcd-40d6-b495-450e5cac3f21'),
  (11, '010200', 1, '고객의 마음을 사로잡는 상세페이지를 제작해 드립니다.\n', '-배너 / 이벤트페이지 / 쇼핑몰상세페이지\n-썸네일/자사물 상세페이지\n-쇼핑몰 상세페이지\n-오픈마켓 상세페이지\n\n무엇이든 디자인하고 책임지고 작업해드리겠습니다.!!', 275000, 1000, '2022-10-06', '썸네일파일이름.png', '5f769e8c-378c-4044-9da4-108c0ce9a360'),
  (12, '010100', 1, '\"2년 연속 크몽 어워즈 대상\" 실력을 보여 드립니다.\n', '2020크몽 어워즈 대상 수상!\n2020~2021년 기준, 크몽 디자인 카테고리 압도적 1위!\n\n압도적인 만족도와 리뷰를 살펴보세요.', 300000, 1000, '2022-10-06', '썸네일파일이름.png', 'efc680ad-1f3f-47aa-a9fe-a84fcc740a2f'),
  (13, '010100', 1, '\"디자인어워드 수상\"검증된 디자이너가 로고디자인 제작해 드립니다.\n', '안녕하세요 Brand03입니다.\n직접 의뢰자님의 브랜드를 분석, 컨설팅하여 원하시는 컨셉에 맞게 고퀄리티 로고를 제작해드립니다. 디자이너 브랜드, 인테리어 전문 회사, 기업, 카페 브랜딩 등 다양한 경험으로 만족스러운 결과물, 합리적인 가격을 약속드리겠습니다.\n\n감각적인 로고(BI, CI) 디자인으로 의뢰자님의 시작을 함께 하겠습니다\n\n포트폴리오를 확인해 주세요 :D', 210000, 1000, '2022-10-06', '썸네일파일이름.png', '50462bb8-e3bf-44d3-a404-00a89667c20a'),
  (14, '010100', 1, '\"로고마스터\"가 당신과 브랜드의 가치에 한끗을 더하다\n', '브랜드(BRAND)는 당신의 비즈니스에 있어서 가장 중요하고 강력한 투자입니다.\n\n지난 10년간 국민은행, SH공사, 현대건설, 한국미니스톱, 기상청 등을 대상으로 다양한 디자인 프로젝트를 진행하였습니다', 79000, 1000, '2022-10-06', '썸네일파일이름.png', 'd829d0b0-4d68-4eec-a050-daa4982f2baf'),
  (15, '010100', 1, '컬러리스트산업기사,한양대출신디자이너가제작해 드립니다.\n', '한양여대 도자기공예과 졸업\n한양대 산업디자인 졸업\n컬러리스트 산업기사 자격증 취득\n오랜경력의 디자인업체 근무\n현재 포토샵,일러스트의 디자인관련 교육중', 175000, 2000, '2022-10-08', '썸네일파일이름.png', '7355f53e-b89d-4847-b64f-08d9d1edfd95\n'),
  (16, '010100', 1, '로고디자인 단 하루 24시간내 로고제작 해드립니다\n', '로고 전문 디자인 밴페로디자인 입니다.\n\n', 130000, 2000, '2022-10-08', '썸네일파일이름.png', '7355f53e-b89d-4847-b64f-08d9d1edfd95');



-- 재능 판매 첨부파일 테이블
insert into product_file (pfno, fname, fpath, pno) values (1, '재능판매첨부파일.png', 'cbb47e3b-07d5-46aa-80b2-45d3f4c53036', 1);
insert into product_file (pfno, fname, fpath, pno) values (2, '재능판매첨부파일.png', 'eab71390-c6f7-4383-8be2-ced30b6c7ea1', 1);
insert into product_file (pfno, fname, fpath, pno) values (3, '재능판매첨부파일.png', 'e37e15ec-2fb6-4e13-ad8c-f98eaa7a7ea8', 2);
insert into product_file (pfno, fname, fpath, pno) values (4, '재능판매첨부파일.png', '16908671-4490-4615-9b4d-ca689f69c437', 2);
insert into product_file (pfno, fname, fpath, pno) values (5, '재능판매첨부파일.png', 'f5569e6b-59c4-48cd-a689-adc32abb2135', 3);
insert into product_file (pfno, fname, fpath, pno) values (6, '재능판매첨부파일.png', '2ac6da8e-30e2-4505-b12a-dde40d23764a', 3);
insert into product_file (pfno, fname, fpath, pno) values (7, '재능판매첨부파일.png', 'e96d3524-226f-4fab-a1fe-223cfeb43aa3', 4);
insert into product_file (pfno, fname, fpath, pno) values (8, '재능판매첨부파일.png', '08ddc509-79a9-4752-a10c-c63a8e63f9f7', 4);
insert into product_file (pfno, fname, fpath, pno) values (9, '재능판매첨부파일.png', '61ca9cda-6b8f-4319-9c7b-f3c4f65cc2b6', 5);
insert into product_file (pfno, fname, fpath, pno) values (10, '재능판매첨부파일.png', '855e19f8-9ee0-47ea-803d-85770991d0e1', 5);
insert into product_file (pfno, fname, fpath, pno) values (11, '재능판매첨부파일.png', 'a410954e-4ffb-4cbb-9602-91c23b74c0e1', 6);
insert into product_file (pfno, fname, fpath, pno) values (12, '재능판매첨부파일.png', '933a30f5-46f0-49b5-bb08-0e3b257b1a66', 6);
insert into product_file (pfno, fname, fpath, pno) values (13, '재능판매첨부파일.png', 'a410954e-4ffb-4cbb-9602-91c23b74c0e1', 6);
insert into product_file (pfno, fname, fpath, pno) values (14, '재능판매첨부파일.png', '933a30f5-46f0-49b5-bb08-0e3b257b1a66', 7);
insert into product_file (pfno, fname, fpath, pno) values (15, '재능판매첨부파일.png', 'a410954e-4ffb-4cbb-9602-91c23b74c0e1', 7);
insert into product_file (pfno, fname, fpath, pno) values (16, '재능판매첨부파일.png', '933a30f5-46f0-49b5-bb08-0e3b257b1a66', 7);
insert into product_file (pfno, fname, fpath, pno) values (17, '재능판매첨부파일.png', 'a410954e-4ffb-4cbb-9602-91c23b74c0e1', 7);
insert into product_file (pfno, fname, fpath, pno) values (18, '재능판매첨부파일.png', '933a30f5-46f0-49b5-bb08-0e3b257b1a66', 8);
insert into product_file (pfno, fname, fpath, pno) values (19, '재능판매첨부파일.png', '933a30f5-46f0-49b5-bb08-0e3b257b1a66', 8);
insert into product_file (pfno, fname, fpath, pno) values (20, '재능판매첨부파일.png', 'a410954e-4ffb-4cbb-9602-91c23b74c0e1', 8);
insert into product_file (pfno, fname, fpath, pno) values (21, '재능판매첨부파일.png', '933a30f5-46f0-49b5-bb08-0e3b257b1a66', 9);
insert into product_file (pfno, fname, fpath, pno) values (22, '재능판매첨부파일.png', 'a410954e-4ffb-4cbb-9602-91c23b74c0e1', 9);
insert into product_file (pfno, fname, fpath, pno) values (23, '재능판매첨부파일.png', '933a30f5-46f0-49b5-bb08-0e3b257b1a66', 9);

-- 주문내역 테이블
insert into product_order (pono, mno, pno, cdt, payment, paycdt, status) values (1, 2, 1, '2022-10-07', '카카오페이', '2022-10-07', '진행중');
insert into product_order (pono, mno, pno, cdt, payment, paycdt, status) values (2, 2, 2, '2022-10-07', '카카오페이', '2022-10-07', '발송중');
insert into product_order (pono, mno, pno, cdt, payment, paycdt, status) values (3, 2, 3, '2022-10-07', '카카오페이', '2022-10-07', '완료');
insert into product_order (pono, mno, pno, cdt, payment, paycdt, status) values (4, 3, 1, '2022-10-07', '카카오페이', '2022-10-08', '진행중');
insert into product_order (pono, mno, pno, cdt, payment, paycdt, status) values (5, 3, 2, '2022-10-07', '카카오페이', '2022-10-08', '발송중');
insert into product_order (pono, mno, pno, cdt, payment, paycdt, status) values (6, 3, 3, '2022-10-07', '카카오페이', '2022-10-08', '완료');

-- 관심사 테이블
insert into interest (mno, pcno) values (2, '010000');
insert into interest (mno, pcno) values (2, '020000');
insert into interest (mno, pcno) values (2, '030000');
insert into interest (mno, pcno) values (3, '040000');
insert into interest (mno, pcno) values (3, '050000');
insert into interest (mno, pcno) values (3, '060000');

-- 주최기관 테이블
insert into contest_organization (orgno, name) values (1, '기업');
insert into contest_organization (orgno, name) values (2, '공공기관');
insert into contest_organization (orgno, name) values (3, '기타');

-- 공모전 카테고리 테이블 
insert into contest_category (ctgno,name) values (1, '기획/아이디어');
insert into contest_category (ctgno,name) values (2, '광고/마케팅');
insert into contest_category (ctgno,name) values (3, '사진/영상/UCC');
insert into contest_category (ctgno,name) values (4, '디자인/순수미술/공예');
insert into contest_category (ctgno,name) values (5, '네이밍/슬로건');
insert into contest_category (ctgno,name) values (6, '캐릭터/만화/게임');
insert into contest_category (ctgno,name) values (7, '건축/건설/인테리어');
insert into contest_category (ctgno,name) values (8, '과학/공학');
insert into contest_category (ctgno,name) values (9, '예체능/패션');
insert into contest_category (ctgno,name) values (10, '전시/페스티벌');
insert into contest_category (ctgno,name) values (11, '문학/시나리오');

-- 공모전 테이블 // 참가인원(=최소인원) 0(제한없음) // 상금(=총 상금액)
INSERT INTO `contest` (`ctstno`, `orgno`, `ctgno`, `title`, `subcont`, `cdt`, `sdate`, `edate`, `vw_cnt`, `org`, `appl`, `cont`, `page`, `size`, `qual`, `team`, `reward`)
VALUES
	(1, 2, 1, '2022 부산교통공사 시민톡톡아이디어 공모전', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n\n', '2022-01-01', '2022-01-01', '2022-12-31', 1, '부산교통공사', '이메일', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n[공모대상] : 부산교통공사(HUmetro)에 관심이 있는 시민 누구나[공모기간] : 2022.01.01. ~ 2022.12.31.\n[시상내역]- 금상 : 200만원- 은상 : 100만원- 동상 : 30만원- 장려상 : 10만원 - 참여상 : 5만원 \n\n※ 등급에 해당하는 제안이 없는 경우에는 해당 등급을 채택하지 않을 수 있음\n※ 결과발표 : 반기별 심사 후 개별 통지※ 「소득세법 제21조 1항 19의 라」에 따라, 10만원 이상의 포상금에 대해서는 기타소득세 제외 후 포상금 지급\n※ 자세한 내용은 공모전사이트 참고\n\n[공모방법]- 온라인 : 부산교통공사 홈페이지 내 시민톡톡아이디어 게시판 등록  부산교통공사 홈페이지 \n- 고객참여 - 시민톡톡아이디어 참여하기\n- 오프라인 : E-mail 및 우편 공모  \nE-mail 주소 : onezero27@humetro.busan.kr  \n우편 주소 : (47353) 부산광역시 부산진구 중앙대로644번길 20, 부산교통공사 기술연구원', 'https://www.humetro.busan.kr/', 0, '대학생/일반인/초등학생/중학생/고등학생', 0, 345),
	(2, 2, 1, '2022 부산교통공사 시민톡톡아이디어 공모전', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n\n', '2022-01-01', '2022-01-01', '2022-12-31', 1, '부산교통공사', '이메일', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n[공모대상] : 부산교통공사(HUmetro)에 관심이 있는 시민 누구나[공모기간] : 2022.01.01. ~ 2022.12.31.\n[시상내역]- 금상 : 200만원- 은상 : 100만원- 동상 : 30만원- 장려상 : 10만원 - 참여상 : 5만원 \n\n※ 등급에 해당하는 제안이 없는 경우에는 해당 등급을 채택하지 않을 수 있음\n※ 결과발표 : 반기별 심사 후 개별 통지※ 「소득세법 제21조 1항 19의 라」에 따라, 10만원 이상의 포상금에 대해서는 기타소득세 제외 후 포상금 지급\n※ 자세한 내용은 공모전사이트 참고\n\n[공모방법]- 온라인 : 부산교통공사 홈페이지 내 시민톡톡아이디어 게시판 등록  부산교통공사 홈페이지 \n- 고객참여 - 시민톡톡아이디어 참여하기\n- 오프라인 : E-mail 및 우편 공모  \nE-mail 주소 : onezero27@humetro.busan.kr  \n우편 주소 : (47353) 부산광역시 부산진구 중앙대로644번길 20, 부산교통공사 기술연구원', 'https://www.humetro.busan.kr/', 0, '대학생/일반인/초등학생/중학생/고등학생', 0, 345),
	(3, 2, 1, '2022 부산교통공사 시민톡톡아이디어 공모전', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n\n', '2022-01-01', '2022-01-01', '2022-12-31', 1, '부산교통공사', '이메일', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n[공모대상] : 부산교통공사(HUmetro)에 관심이 있는 시민 누구나[공모기간] : 2022.01.01. ~ 2022.12.31.\n[시상내역]- 금상 : 200만원- 은상 : 100만원- 동상 : 30만원- 장려상 : 10만원 - 참여상 : 5만원 \n\n※ 등급에 해당하는 제안이 없는 경우에는 해당 등급을 채택하지 않을 수 있음\n※ 결과발표 : 반기별 심사 후 개별 통지※ 「소득세법 제21조 1항 19의 라」에 따라, 10만원 이상의 포상금에 대해서는 기타소득세 제외 후 포상금 지급\n※ 자세한 내용은 공모전사이트 참고\n\n[공모방법]- 온라인 : 부산교통공사 홈페이지 내 시민톡톡아이디어 게시판 등록  부산교통공사 홈페이지 \n- 고객참여 - 시민톡톡아이디어 참여하기\n- 오프라인 : E-mail 및 우편 공모  \nE-mail 주소 : onezero27@humetro.busan.kr  \n우편 주소 : (47353) 부산광역시 부산진구 중앙대로644번길 20, 부산교통공사 기술연구원', 'https://www.humetro.busan.kr/', 0, '대학생/일반인/초등학생/중학생/고등학생', 0, 345),
	(4, 2, 1, '2022 부산교통공사 시민톡톡아이디어 공모전', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n\n', '2022-01-01', '2022-01-01', '2022-12-31', 1, '부산교통공사', '이메일', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n[공모대상] : 부산교통공사(HUmetro)에 관심이 있는 시민 누구나[공모기간] : 2022.01.01. ~ 2022.12.31.\n[시상내역]- 금상 : 200만원- 은상 : 100만원- 동상 : 30만원- 장려상 : 10만원 - 참여상 : 5만원 \n\n※ 등급에 해당하는 제안이 없는 경우에는 해당 등급을 채택하지 않을 수 있음\n※ 결과발표 : 반기별 심사 후 개별 통지※ 「소득세법 제21조 1항 19의 라」에 따라, 10만원 이상의 포상금에 대해서는 기타소득세 제외 후 포상금 지급\n※ 자세한 내용은 공모전사이트 참고\n\n[공모방법]- 온라인 : 부산교통공사 홈페이지 내 시민톡톡아이디어 게시판 등록  부산교통공사 홈페이지 \n- 고객참여 - 시민톡톡아이디어 참여하기\n- 오프라인 : E-mail 및 우편 공모  \nE-mail 주소 : onezero27@humetro.busan.kr  \n우편 주소 : (47353) 부산광역시 부산진구 중앙대로644번길 20, 부산교통공사 기술연구원', 'https://www.humetro.busan.kr/', 0, '대학생/일반인/초등학생/중학생/고등학생', 0, 345),
	(5, 2, 1, '2022 부산교통공사 시민톡톡아이디어 공모전', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n\n', '2022-01-01', '2022-01-01', '2022-12-31', 1, '부산교통공사', '이메일', '[공모내용] : 절대안전, 건전재정, 고객행복, 사회적가치에 부합하는 부산 지하철에 관한 모든 아이디어\n[공모대상] : 부산교통공사(HUmetro)에 관심이 있는 시민 누구나[공모기간] : 2022.01.01. ~ 2022.12.31.\n[시상내역]- 금상 : 200만원- 은상 : 100만원- 동상 : 30만원- 장려상 : 10만원 - 참여상 : 5만원 \n\n※ 등급에 해당하는 제안이 없는 경우에는 해당 등급을 채택하지 않을 수 있음\n※ 결과발표 : 반기별 심사 후 개별 통지※ 「소득세법 제21조 1항 19의 라」에 따라, 10만원 이상의 포상금에 대해서는 기타소득세 제외 후 포상금 지급\n※ 자세한 내용은 공모전사이트 참고\n\n[공모방법]- 온라인 : 부산교통공사 홈페이지 내 시민톡톡아이디어 게시판 등록  부산교통공사 홈페이지 \n- 고객참여 - 시민톡톡아이디어 참여하기\n- 오프라인 : E-mail 및 우편 공모  \nE-mail 주소 : onezero27@humetro.busan.kr  \n우편 주소 : (47353) 부산광역시 부산진구 중앙대로644번길 20, 부산교통공사 기술연구원', 'https://www.humetro.busan.kr/', 0, '대학생/일반인/초등학생/중학생/고등학생', 0, 345);

-- 공모전 첨부파일 테이블
INSERT INTO `contest_file` (`ctstfno`, `fname`, `fpath`, `ctstno`)
VALUES
	(1, '공모전첨부파일1', '6721b861-5010-46be-9abe-8b7b446b037e', 1),
	(2, '공모전첨부파일2', '6721b861-5010-46be-9abe-8b7b446b037e', 2),
	(3, '공모전첨부파일3', '6721b861-5010-46be-9abe-8b7b446b037e', 3),
	(4, '공모전첨부파일1', '6721b861-5010-46be-9abe-8b7b446b037e', 4),
	(5, '공모전첨부파일2', '6721b861-5010-46be-9abe-8b7b446b037e', 4);

-- 팀원모집 테이블
INSERT INTO `team` (`tno`, `ctstno`, `mno`, `cont`, `cdt`)
VALUES
	(1, 1, 1, '안녕하세요!\n저는 편집을 많이 해봤고 수상경험도 있습니다!\n제가 촬영이 부족하여 촬영을 잘 해 주시는 분을 구하고 있습니다!\n많은 관심 부탁드리고 궁금하신 점이 있으시다면 아래 딧글을 달아주세요!\n\n', '2022-10-08'),
	(2, 2, 2, '안녕하세요!\n저는 편집을 많이 해봤고 수상경험도 있습니다!\n제가 촬영이 부족하여 촬영을 잘 해 주시는 분을 구하고 있습니다!\n많은 관심 부탁드리고 궁금하신 점이 있으시다면 아래 딧글을 달아주세요!\n\n', '2022-10-08'),
	(3, 3, 3, '안녕하세요!\n저는 편집을 많이 해봤고 수상경험도 있습니다!\n제가 촬영이 부족하여 촬영을 잘 해 주시는 분을 구하고 있습니다!\n많은 관심 부탁드리고 궁금하신 점이 있으시다면 아래 딧글을 달아주세요!\n\n', '2022-10-08'),
	(4, 4, 4, '안녕하세요!\n저는 편집을 많이 해봤고 수상경험도 있습니다!\n제가 촬영이 부족하여 촬영을 잘 해 주시는 분을 구하고 있습니다!\n많은 관심 부탁드리고 궁금하신 점이 있으시다면 아래 딧글을 달아주세요!\n\n', '2022-10-08'),
	(5, 4, 5, '안녕하세요!\n저는 편집을 많이 해봤고 수상경험도 있습니다!\n제가 촬영이 부족하여 촬영을 잘 해 주시는 분을 구하고 있습니다!\n많은 관심 부탁드리고 궁금하신 점이 있으시다면 아래 딧글을 달아주세요!\n\n', '2022-10-08');

-- 모집분야 테이블 // 상태(0: 모집중 / 1: 모집완료)
INSERT INTO `team_field` (`tfno`, `tno`, `name`, `size`, `type`)
VALUES
	(1, 1, '찰영', 1, 0),
	(2, 1, '음향', 2, 0),
	(3, 1, '편집', 1, 1),
	(4, 1, '찰영', 1, 0),
	(5, 1, '편집', 3, 1);

-- 팀원모집분야지원 테이블
INSERT INTO `team_field_member` (`tfmno`, `tfno`, `mno`, `cont`, `cdt`, `type`)
VALUES
	(1, 1, 1, '안녕하세요\n촬영담장자로 근무하면서 어떤 촬영자료들이 영상에 적합한지 그리고 더 많이 활용되는지에 대한 능력을 가지고있습니다\n잘부탁드립니다', '2022-01-01', 0),
(2, 1, 1, '안녕하세요\n촬영담장자로 근무하면서 어떤 촬영자료들이 영상에 적합한지 그리고 더 많이 활용되는지에 대한 능력을 가지고있습니다\n잘부탁드립니다', '2022-01-01', 0),
(3, 1, 1, '안녕하세요\n촬영담장자로 근무하면서 어떤 촬영자료들이 영상에 적합한지 그리고 더 많이 활용되는지에 대한 능력을 가지고있습니다\n잘부탁드립니다', '2022-01-01', 0),
(4, 1, 1, '안녕하세요\n촬영담장자로 근무하면서 어떤 촬영자료들이 영상에 적합한지 그리고 더 많이 활용되는지에 대한 능력을 가지고있습니다\n잘부탁드립니다', '2022-01-01', 0),
(5, 1, 1, '안녕하세요\n촬영담장자로 근무하면서 어떤 촬영자료들이 영상에 적합한지 그리고 더 많이 활용되는지에 대한 능력을 가지고있습니다\n잘부탁드립니다', '2022-01-01', 0);

-- 1대1 대화 테이블
insert into message(msgno, mno, mno2, cont, type, cdt) values (1, 1, 6, '안녕하세요.반갑습니다.의뢰하려고하는데요. 시간이 되실까요?', 1, '2022-01-01');
insert into message(msgno, mno, mno2, cont, type, cdt) values (2, 2, 5, '제 일정이 안되어서 어렵습니다.', 0, '2022-01-06');
insert into message(msgno, mno, mno2, cont, type, cdt) values (3, 3, 4, '이 재능 구매하고싶습니다', 1, '2022-06-03');
insert into message(msgno, mno, mno2, cont, type, cdt) values (4, 4, 3, '구매확정 부탁드려요.', 0, '2022-05-09');
insert into message(msgno, mno, mno2, cont, type, cdt) values (5, 5, 2, '이 가격으로 해주는 곳도 없을거에요.', 0, '2022-03-04');
insert into message(msgno, mno, mno2, cont, type, cdt) values (6, 6, 1, '15일뒤까지 가능할까요?', 1, '2022-05-03');

-- 1대1 대화 첨부파일 테이블
insert into message_file (msgfno, fname, fpath, msgno) values (1, '1대1대화첨부파일1', '6af7752f-f889-4e40-aed8-2947fc5418f1', 1);
insert into message_file (msgfno, fname, fpath, msgno) values (2, '1대1대화첨부파일2', '6af7752f-f889-4e40-aed8-2947fc5418f1', 2);
insert into message_file (msgfno, fname, fpath, msgno) values (3, '1대1대화첨부파일3', '6af7752f-f889-4e40-aed8-2947fc5418f1', 3);
insert into message_file (msgfno, fname, fpath, msgno) values (4, '1대1대화첨부파일4', '6af7752f-f889-4e40-aed8-2947fc5418f1', 4);
insert into message_file (msgfno, fname, fpath, msgno) values (5, '1대1대화첨부파일5', '6af7752f-f889-4e40-aed8-2947fc5418f1', 5);
insert into message_file (msgfno, fname, fpath, msgno) values (6, '1대1대화첨부파일6', '6af7752f-f889-4e40-aed8-2947fc5418f1', 6);

-- 관리자 테이블
insert into admin_member (amno, id, pwd) values (1, 'admin1@test.com', sha2('1111',256));
insert into admin_member (amno, id, pwd) values (2, 'admin2@test.com', sha2('1111',256));

-- 공지사항 테이블
insert into notice (ntcno, title, cont, cdt, vw_cnt, fname, fpath) values (1, 'ONEMOA를 사칭하는 행위, 조심하세요!', '안녕하세요, ONEMOA입니다. 최근 ONEMOA를 사칭한 인물 및 업체로부터의 피해 사례가 발생하고 있습니다. 위와 같은 행위는 크몽 서비스 이용에 혼란을 주고, 더불어 물질적인 피해를 야기할 수 있어 회원님들의 각별한 주의가 필요합니다.', '22-10-08', 10, '공지사항 첨부파일.png','b1fe7835-1fcd-40d6-b495-450e5cac3f21');
insert into notice (ntcno, title, cont, cdt, vw_cnt, fname, fpath) values (2, '패널티 정책 안내', '안녕하세요, ONEMOA입니다. ONEMOA는 거래의 안전성과 신뢰성을 확보하기 위하여 서비스 이용 중 발생할 수 있는 규정 위반 사유를 안내하고, 이용약관 및 정책에 따라 상응하는 조치를 취하고 있습니다. 회원님의 원활한 서비스 이용을 바라며 명문화된 페널티 정책을 안내해 드립니다. 자세한 내용은 아래의 공지를 확인해 주시기 바랍니다.', '22-10-08', 10, '공지사항 첨부파일.png','b1fe7835-1fcd-40d6-b495-450e5cac3f21');

-- FAQ 테이블
insert into faq (faqno, title, cont, type) value(1, '로그인이 제대로 되지 않을 경우.', 'ID와 비밀번호가 맞는지 확인해봅니다.', '관리자');
insert into faq (faqno, title, cont, type) value(2, '결제가 제대로 되지 않는 경우.', '결제가 제대로 되는지 확인해봅니다.', '관리자');

-- 문의 카테고리 테이블
insert into qna_category (qnacno, name) value(1, '상품 결제 관련');
insert into qna_category (qnacno, name) value(2, '서비스 관련');
insert into qna_category (qnacno, name) value(3, '가입/인증 관련');
insert into qna_category (qnacno, name) value(4, '계정 정보 관련');
insert into qna_category (qnacno, name) value(5, '개인 정보 관련');

-- 1 : 1 문의 테이블
insert into qna (qnano, qnacno, title, cont, cdt, mno, answer, answercdt) value(1, 1, '상품 결제가 안 됩니다', '결제 버튼을 눌렀는데도 제대로 되지 않습니다.', '2022-10-09', 1, '결제 버튼을 다시 눌러보시기 바랍니다.', '2022-10-10');
insert into qna (qnano, qnacno, title, cont, cdt, mno, answer, answercdt) value(2, 2, '서비스 품질이 좋지 않습니다.', '해당 게시판의 UI가 이상합니다. 첨부파일 확인 부탁드립니다.', '2022-10-09', 2, NULL, NULL);
insert into qna (qnano, qnacno, title, cont, cdt, mno, answer, answercdt) value(3, 5, '이 사이트의 보안은 어떤가요?', '요즘 들어 해킹을 당하는 웹사이트가 많은데 ONEMOA의 보안 수준은 어느 정도인가요?', '2022-10-09', 4, '걱정하지 않으셔도 됩니다.', '2022-10-10');

-- 1 : 1 문의 첨부파일 테이블
insert into qna_file(qnafno, fname, fpath, qnano) value(1,'1:1문의 첨부파일.jpg', 'd829d0b0-4d68-4eec-a050-daa4982f2baf', 2);

-- 포트폴리오
insert into portfolio (ptno,mno,title,cont,cdt) values (1,1,"회원1의 포트폴리오제목", "디자인 포트폴리오입니다.", "2022-01-03");
insert into portfolio (ptno,mno,title,cont,cdt) values (2,2,"회원2의 포트폴리오제목", "사진포트폴리오입니다.","2022-06-27");
insert into portfolio (ptno,mno,title,cont,cdt) values (3,2,"회원2의 포트폴리오제목", "영상 편집 포트폴리오입니다.","2022-07-03");
insert into portfolio (ptno,mno,title,cont,cdt) values (4,3,"회원3의 포트폴리오제목a", "로고 제작 포트폴리오입니다.","2022-08-17");
insert into portfolio (ptno,mno,title,cont,cdt) values (5,3,"회원3의 포트폴리오제목b", "일러스트 포트폴리오입니다.","2022-09-10");
insert into portfolio (ptno,mno,title,cont,cdt) values (6,3,"회원3의 포트폴리오제목c", "배너 제작 포트폴리오입니다.","2022-09-24");
insert into portfolio (ptno,mno,title,cont,cdt) values (7,4,"회원4의 포트폴리오제목", "번역 포트폴리오입니다.","2022-10-01");
insert into portfolio (ptno,mno,title,cont,cdt) values (8,5,"회원5의 포트폴리오제목", "영상 제작 포트폴리오입니다.","2022-10-06");
insert into portfolio (ptno,mno,title,cont,cdt) values (9,2,"웨딩사진 포트폴리오(제목)", "사진포트폴리오입니다.","2022-10-07");
insert into portfolio (ptno,mno,title,cont,cdt) values (10,3,"로고 제작 포트폴리오(제목)", "로고 제작 포트폴리오입니다.","2022-10-07");
insert into portfolio (ptno,mno,title,cont,cdt) values (11,1,"멋진 디자인 포트폴리오(제목)", "디자인 포트폴리오입니다.", "2022-10-08");
insert into portfolio (ptno,mno,title,cont,cdt) values (12,2,"독립영화제 출품작 포트폴리오(제목)", "영상 편집 포트폴리오입니다.","2022-10-08");

-- 포트폴리오 첨부파일
insert into portfolio_file (ptfno, fname, fpath, ptno) values (1,"포트폴리오 첨부파일a","ab93278e-72ba-42dc-a468-010435c31d2b",1);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (2,"포트폴리오 첨부파일b","f388dc55-8ea9-4878-a8ab-66df9ffcf5f9",2);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (3,"포트폴리오 첨부파일c","99526b3f-cb9e-4c06-8d2f-b0a883f483fa",3);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (4,"포트폴리오 첨부파일d","2766082d-4b4b-40db-b874-ac2ca856b478",4);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (5,"포트폴리오 첨부파일e","e12d2e96-406e-4ca3-9913-9728ab557bf2",5);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (6,"포트폴리오 첨부파일f","8b22566d-ef10-4a24-816b-f89597b777e5",6);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (7,"포트폴리오 첨부파일g","5482da81-3c09-4a8c-9d5f-0e428d08c962",7);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (8,"포트폴리오 첨부파일a","b6496a5b-ad94-4ca0-af57-86aae3a0a64a",8);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (9,"포트폴리오 첨부파일e","eb0cacf9-f084-4797-9493-008373eb659b",9);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (10,"포트폴리오 첨부파일g","3b76ef98-5dc9-41c0-af90-60442214ded8",10);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (11,"포트폴리오 첨부파일s","b8922a36-7eb7-42f0-93c9-eef23585ba4c",11);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (12,"포트폴리오 첨부파일d","86f1f023-c1bc-401b-af98-1732aa23201f",12);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (13,"포트폴리오 첨부파일e","1ef327f7-5425-4f26-b117-e4f0b24e6800",12);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (14,"포트폴리오 첨부파일n","360ba9cb-a55b-4f2f-b879-952a8153c2f5",3);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (15,"포트폴리오 첨부파일hh","a3075014-48b9-4860-8a63-7849bde4fe5b",6);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (16,"포트폴리오 첨부파일e","5f8edfb4-e202-44d3-8673-4ce77f2d8723",6);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (17,"포트폴리오 첨부파일u","70f795e7-fcd0-4f21-b8ca-4c387b4a63c5",7);


-- wishlist
insert into wishlist (mno, pno) values (1,1);
insert into wishlist (mno, pno) values (1,2);
insert into wishlist (mno, pno) values (1,3);
insert into wishlist (mno, pno) values (2,1);
insert into wishlist (mno, pno) values (2,2);

-- product_review
insert into product_review (prno, mno, pno, title, cont, cdt, scope) values (1, 1, 1, "로고디자인", "연락도 빨랐고 생각했던 것 보다 더 잘 만들어 주셨어요!","2022-10-09", 5);
insert into product_review (prno, mno, pno, title, cont, cdt, scope) values (2, 2, 5, "로고디자인", "초안을 여러개 볼 수 있어서 좋았습니다.","2022-10-09", 4);
insert into product_review (prno, mno, pno, title, cont, cdt, scope) values (3, 3, 2, "로고디자인", "수정을 여러번 요청해도 다 들어주셨고 작업해주신거 정말 맘에 들어요","2022-10-09", 5);
insert into product_review (prno, mno, pno, title, cont, cdt, scope) values (4, 4, 3, "로고디자인", "연락이 정말 빨랐고 작업 맘에 들어요","2022-10-09", 5);
insert into product_review (prno, mno, pno, title, cont, cdt, scope) values (5, 5, 4, "로고디자인", "너무 친절했고 마감기한 딱 맞춰주셨어요","2022-10-09", 4);

-- product_review_file
insert into product_review_file (prfno, fname, fpath, prno) values (1, "첨부파일제목1", "650c0ef0-8e6b-405f-aadc-6655f78b337a",1);
insert into product_review_file (prfno, fname, fpath, prno) values (2, "첨부파일제목2", "4299e4e1-53a4-4cb1-8058-679be0880bd8",2);
insert into product_review_file (prfno, fname, fpath, prno) values (3, "첨부파일제목3", "ca874e1c-a77b-446b-9509-08b8e19a3303",3);
insert into product_review_file (prfno, fname, fpath, prno) values (4, "첨부파일제목4", "e0dd4228-e0cd-476d-96b8-341610d0539d",4);
insert into product_review_file (prfno, fname, fpath, prno) values (5, "첨부파일제목5", "b4eb46cf-ca76-4546-a73a-a999a34d37c1",5);