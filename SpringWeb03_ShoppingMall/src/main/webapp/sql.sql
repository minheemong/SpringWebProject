select * from ORDER_DETAIL;

select * from qna;
select * from product;
select * from cart;
select * from cart_view;
select * from orders;
select * from order_view;
select * from WORKER;
select * from member;

update ORDER_DETAIL set result='2' where oseq=21;


drop table qna purge;

create table qna(
qseq number(5) primary key, -- 글번호
subject varchar2(300), -- 제목
content varchar2(1000), -- 문의내용
reply varchar2(1000), -- 답변내용
id varchar2(20), --작성자(FK: member.id)
rep char(1) default '1', -- 1:답변무 2:답변유
indate date default sysdate -- 작성일
);


drop sequence qna_seq;
create sequence qna_seq start with 1;


insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'배송문의입니다','현재 배송상태와 예상배송일을 답변 부탁합니다','one');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'환불문의입니다','환불절차 안내부탁드려요. 배송사 선택은 어떻게 되는지도 알려주세요','one');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'사이즈 교환하고싶어요','사이즈가 예상보다 작습니다. 교환절차 안내해주세요','two');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'배송이 많이 지연되고 있습니다','언제 받을 수 있나요?','scott');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'상품문의입니다','소재가 어떻게 되나요?','scott');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'배송문의입니다','현재 배송상태와 예상배송일을 답변 부탁합니다','one');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'환불문의입니다','환불절차 안내부탁드려요. 배송사 선택은 어떻게 되는지도 알려주세요','one');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'사이즈 교환하고싶어요','사이즈가 예상보다 작습니다. 교환절차 안내해주세요','two');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'배송이 많이 지연되고 있습니다','언제 받을 수 있나요?','scott');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextval,'상품문의입니다','소재가 어떻게 되나요?','scott');


insert into product(pseq,name,kind,price,price2,price3,content,image)
values(product_seq.nextVal,'크로그다일부츠','2',40000,50000,10000,'오리지날 크로그다일부츠입니다','w2.jpg');
insert into product(pseq,name,kind,price,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'롱부츠','2',40000,50000,10000,'따듯한 롱부츠입니다.','w-28.jpg','n');
insert into product(pseq,name,kind,price,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'힐','1',10000,12000,2000,'여성전용 힐','w-26.jpg','n');
insert into product(pseq,name,kind,price,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'슬리퍼','4',5000,5500,500,'편안한 슬리퍼입니다','w-25.jpg','y');
insert into product(pseq,name,kind,price,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'회색힐','1',10000,12000,2000,'여성용 힐','w9.jpg','n');
insert into product(pseq,name,kind,price,price2,price3,content,image)
values(product_seq.nextVal,'여성부츠','2',12000,18000,6000,'여성용 부츠','w-4.jpg');
insert into product(pseq,name,kind,price,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'핑크샌달','3',5000,5500,500,'사계절용 샌달입니다','w-10.jpg','y');
insert into product(pseq,name,kind,price,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'슬리퍼','3',5000,5500,500,'편안한 슬리퍼입니다','w-11.jpg','y');
insert into product(pseq,name,kind,price,price2,price3,content,image)
values(product_seq.nextVal,'스니커즈','4',15000,20000,5000,'활동성이 좋은 스니커즈입니다','w1.jpg');
insert into product(pseq,name,kind,price,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'샌달','3',5000,5500,500,'사계절용 샌달입니다','w-09.jpg','n');
insert into product(pseq,name,kind,price,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'스니커즈','5',15000,20000,5000,'활동성이 좋은 스니커즈입니다','w-05.jpg','n');



