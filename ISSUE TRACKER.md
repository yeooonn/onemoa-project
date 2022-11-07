
1. getter/setter오류

오류 코드
Exception evaluating SpringEL expression: ~

원인
해당 문법의 getter setter가 적용되지 않아서 발생하는 문제

2. Spring Mapper 오류

오류 코드
There was an unexpected error (type=Internal Server Error, status=500).
Error resolving template [프로그램 경로], template might not exist or might not be accessible by any of the configured Template Resolvers

원인
xml에서 select id = ~~ 에 적은 변수가 @GetMapping 및 html의 파일명과 다를 경우 발생하는 문제

3. Spring Mapper 오류

오류 코드
org.apache.ibatis.binding.BindingException: Invalid bound statement (not found):

원인
해당 java class에 귀속된 xml의 mapper namespace가 오류일 경우 발생하는 문제

