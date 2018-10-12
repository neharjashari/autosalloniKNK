drop database AutosalloniRoyce;
create database AutosalloniRoyce;

use AutosalloniRoyce;

create table veturat(
	vId integer primary key auto_increment,
    brendi varchar(30) not null,
    modeli varchar(30) not null,
    motori varchar(30) not null,
    tipi varchar(30) not null,
    ngjyra varchar(20) not null,
    vitiProdhimit varchar(30) not null,
    numriDyerve integer not null,
    karburanti varchar(30) not null,
    transmetuesi varchar(20) not null,
    targat varchar(30) not null,
    cmimi varchar(20) not null
    );
    
create table perdoruesit(
	pId integer primary key auto_increment,
    emri varchar(30) not null,
    mbiemri varchar(30) not null,
    gjinia varchar(30) not null,
    username varchar(30) not null unique,
    email varchar(30) not null unique,
    fjalekalimi varchar(60) not null,
    nrTel varchar(30) not null,
    adresa varchar(30) not null,
    qyteti varchar(30) not null
    );
    
create table shitjet(
	sID integer primary key auto_increment,
    brendi varchar(30) not null,
    modeli varchar(30) not null,
    cmimi varchar(20) not null,
    dataShitjes date,
    kohaShitjes time
);
    
create table blerjet(
	bId integer primary key auto_increment,
	vId integer,
    dataBlerjes date,
    kohaBlerjes time
    );
    


    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Mercedes-Benz','C200','2.2 CDI','Sedan','E kuqe','2002','5','Nafte','Automatik','Kosovare','6500');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Volkswagen','Golf 5','1.6 FSI','Hatchback','E zeze','2004','5','Benzine','Manual','Kosovare','5400');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Volkswagen','Passat','1.9 TDI','Sedan','E kalter','2011','5','Nafte','Manual','Te huaja','8500');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('BMW','530D','3.0','Sedan','E zeze','2006','5','Nafte','Automatik','Kosovare','7200');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Opel','Astra','1.7 CDTI','Hatchback','E hirte','2009','3','Nafte','Manual','Te huaja','5800');
	INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Opel','Corsa','1.2','Hatchback','E kalter','2005','3','Benzine','Manual','Kosovare','5200');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Mercedes-Benz','E220','2.2 CDI','Sedan','E hirte','2013','5','Nafte','Automatik','Kosovare','19500');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Mini','Cooper','1.6','Veture e vogel','E verdhe','2002','3','Benzine','Manual','Shqiptare','3600');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('BMW','330D','2.0','Sedan','E kuqe','2005','5','Nafte','Automatik','Kosovare','6800');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Volkswagen','Jetta','2.0 TDI','Sedan','E zeze','2009','5','Nafte','Manual','Te huaja','5500');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Mercedes-Benz','C220','2.2 CDI','Coupe','E hirte','2000','3','Nafte','Automatik','Kosovare','4800');
	INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Volkswagen','Golf 4','1.8 FSI','Hatchback','E kuqe','2001','3','Benzine','Automatik','Kosovare','2800');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Volkswagen','Golf 4','1.9 TDI','Hatchback','E zeze','2003','5','Nafte','Manual','Kosovare','3800');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Opel','Insignia','1.9 CDTI','Sedan','E hirte','2005','5','Nafte','Automatik','Kosovare','6700');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Peugeot','407','2.0 HDI','Sedan','E zeze','2006','5','Nafte','Manual','Kosovare','6500');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Volkswagen','Sharan','1.9 TDI','Minivan','E kalter','2010','5','Nafte','Manual','Te huaja','7000');
	INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Peugeot','206','1.4 HDI','Kabriolet','E hirte','2002','3','Nafte','Manual','Kosovare','2800');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('BMW','120d','1.9','Hatchback','E zeze','2007','5','Nafte','Automatik','Kosovare','7200');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Range Rover','Evoque','2.2','SUV','E bardhe','2008','5','Nafte','Automatik','Kosovare','12500');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Mercedes-Benz','ML320','2.2 CDI','SUV','E zeze','2006','5','Nafte','Automatik','Kosovare','9500');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Mercedes-Benz','GLE350','2.2 CDI','SUV','E kuqe','2016','5','Nafte','Automatik','Kosovare','32000');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Audi','A5','3.0 TDI','Coupe','E kuqe','2011','3','Nafte','Automatik','Kosovare','16500');
	INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Audi','Q7','3.0 TDI','SUV','E zeze','2008','5','Nafte','Automatik','Te huaja','10000');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Volkswagen','Passat','2.0 TDI','Universal','E bardhe','2006','5','Nafte','Manual','Kosovare','6500');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Audi','A3','1.9 TDI','Hatchback','E zeze','2008','5','Nafte','Automatik','Kosovare','7200');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Opel','Signum','2.2 CDTI','Universal','E kuqe','2004','5','Nafte','Manual','Kosovare','5800');
    INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Volkswagen','Golf VI','2.0 TDI','Hatchback','E zeze','2010','5','Nafte','Manual','Kosovare','11000');
   

DELIMITER $$
create trigger pasBlerjes after insert on veturat for each row
begin
	insert into blerjet(vId,dataBlerjes,kohaBlerjes) values (NEW.vId, CURDATE(),CURTIME());
end; $$
DELIMITER ;

DELIMITER $$
create trigger pasShitjes after delete on veturat for each row
begin
	insert into shitjet(brendi,modeli,cmimi,dataShitjes,kohaShitjes) values (OLD.brendi,OLD.modeli,OLD.cmimi,CURDATE(),CURTIME());
end; $$
DELIMITER ;



select * from perdoruesit;
#select * from veturat;

/*select v.brendi, v.modeli, v.cmimi, b.dataBlerjes 
from veturat v, blerjet b
where v.vid = b.vid;
*/

#INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,targat,cmimi) VALUES ('Mercedes-Benz','C220','2.2 CDI','Sedan','E Hirte','2005','5','Nafte','Automatik','Kosovare','7500');
#select * from blerjet;

#DELETE FROM veturat WHERE vId=28;
#select * from shitjet;


