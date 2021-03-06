-- begin LIBRARY_BOOK
alter table LIBRARY_BOOK add constraint LIBRARY_BOOK_LITERATURE_TYPE foreign key (LITERATURE_TYPE_ID) references LIBRARY_LITERATURE_TYPE(ID)^
create index IDX_LIBRARY_BOOK_LITERATURE_TYPE on LIBRARY_BOOK (LITERATURE_TYPE_ID)^
-- end LIBRARY_BOOK

-- begin LIBRARY_BOOK_PUBLICATION
alter table LIBRARY_BOOK_PUBLICATION add constraint LIBRARY_BOOK_PUBLICATION_BOOK foreign key (BOOK_ID) references LIBRARY_BOOK(ID)^
alter table LIBRARY_BOOK_PUBLICATION add constraint LIBRARY_BOOK_PUBLICATION_PUBLISHER foreign key (PUBLISHER_ID) references LIBRARY_PUBLISHER(ID)^
alter table LIBRARY_BOOK_PUBLICATION add constraint LIBRARY_BOOK_PUBLICATION_TOWN foreign key (TOWN_ID) references LIBRARY_TOWN(ID)^
create index IDX_LIBRARY_BOOK_PUBLICATION_BOOK on LIBRARY_BOOK_PUBLICATION (BOOK_ID)^
create index IDX_LIBRARY_BOOK_PUBLICATION_TOWN on LIBRARY_BOOK_PUBLICATION (TOWN_ID)^
create index IDX_LIBRARY_BOOK_PUBLICATION_PUBLISHER on LIBRARY_BOOK_PUBLICATION (PUBLISHER_ID)^

-- end LIBRARY_BOOK_PUBLICATION
-- begin LIBRARY_BOOK_INSTANCE
alter table LIBRARY_BOOK_INSTANCE add constraint LIBRARY_BOOK_INSTANCE_BOOK_PUBLICATION foreign key (BOOK_PUBLICATION_ID) references LIBRARY_BOOK_PUBLICATION(ID)^
alter table LIBRARY_BOOK_INSTANCE add constraint LIBRARY_BOOK_INSTANCE_LIBRARY_DEPARTMENT foreign key (LIBRARY_DEPARTMENT_ID) references LIBRARY_LIBRARY_DEPARTMENT(ID)^
create index IDX_LIBRARY_BOOK_INSTANCE_LIBRARY_DEPARTMENT on LIBRARY_BOOK_INSTANCE (LIBRARY_DEPARTMENT_ID)^
create index IDX_LIBRARY_BOOK_INSTANCE_BOOK_PUBLICATION on LIBRARY_BOOK_INSTANCE (BOOK_PUBLICATION_ID)^

-- end LIBRARY_BOOK_INSTANCE
-- begin LIBRARY_BOOK_AUTHOR_LINK
alter table LIBRARY_BOOK_AUTHOR_LINK add constraint FK_LBAL_BOOK foreign key (BOOK_ID) references LIBRARY_BOOK (ID)^
alter table LIBRARY_BOOK_AUTHOR_LINK add constraint FK_LBAL_AUTHOR foreign key (AUTHOR_ID) references LIBRARY_AUTHOR (ID)^
-- end LIBRARY_BOOK_AUTHOR_LINK
