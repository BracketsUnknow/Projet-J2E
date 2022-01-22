""/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de cr�ation :  28/12/2021 23:23:43                     */
/*==============================================================*/

drop database if exists ALBUMPHOTO;

create database ALBUMPHOTO;
use ALBUMPHOTO;

drop table if exists ACCEDER;

drop table if exists ALBUM;

drop table if exists IMAGE;

drop table if exists POSSEDER;

drop table if exists THEME;

drop table if exists UTILISATEUR;

/*==============================================================*/
/* Table : ACCEDER                                              */
/*==============================================================*/
create table ACCEDER
(
   ID_UTILISATEUR       int not null,
   ID_ALBUM             int not null,
   primary key (ID_UTILISATEUR, ID_ALBUM)
);

/*==============================================================*/
/* Table : ALBUM                                                */
/*==============================================================*/
create table ALBUM
(
   ID_ALBUM             int not null auto_increment,
   ID_UTILISATEUR       int not null,
   ID_THEME             int not null,
   NOM                  varchar(50),
   DETAIL               text,
   VISIBILITE           ENUM("public","prive") DEFAULT "prive",
   primary key (ID_ALBUM)
);

/*==============================================================*/
/* Table : IMAGE                                                */
/*==============================================================*/
create table IMAGE
(
   ID_IMAGE             int not null auto_increment,
   ID_ALBUM             int not null,
   TITRE                varchar(25),
   DESCRIPTION          text,
   HAUTEUR              decimal,
   DATE_DE_CREATION     datetime,
   DATE_DE_MISE_A_JOUR  datetime,
   LARGEUR              decimal,
   ACCESSIBILITE        ENUM("public","prive") DEFAULT "prive",
   LIEN_FICHIER_IMAGE   varchar(200),
   primary key (ID_IMAGE)
);


/*==============================================================*/
/* Table : THEME                                                */
/*==============================================================*/
create table THEME
(
   ID_THEME             int not null auto_increment,
   LIBELLE              varchar(25),
   primary key (ID_THEME)
);

/*==============================================================*/
/* Table : UTILISATEUR                                          */
/*==============================================================*/
create table UTILISATEUR
(
   ID_UTILISATEUR       int not null auto_increment,
   NOM                  varchar(50),
   PRENOM               varchar(50),
   EMAIL                varchar(100) unique,
   PROFIL               varchar(100),
   PASSWORD             varchar(50),
   FONCTION             ENUM("utilisateur_simple","administrateur") DEFAULT "utilisateur_simple",
   primary key (ID_UTILISATEUR)
);

alter table ACCEDER add constraint FK_ACCEDER foreign key (ID_UTILISATEUR)
      references UTILISATEUR (ID_UTILISATEUR) on delete restrict on update restrict;

alter table ACCEDER add constraint FK_ACCEDER2 foreign key (ID_ALBUM)
      references ALBUM (ID_ALBUM) on delete restrict on update restrict;

alter table ALBUM add constraint FK_ETRE_PROPRIETAIRE foreign key (ID_UTILISATEUR)
      references UTILISATEUR (ID_UTILISATEUR) on delete restrict on update restrict;

alter table IMAGE add constraint FK_PLACER foreign key (ID_ALBUM)
      references ALBUM (ID_ALBUM) on delete restrict on update restrict;

alter table ALBUM add constraint FK_POSSEDER foreign key (ID_THEME)
      references THEME (ID_THEME) on delete restrict on update restrict;


