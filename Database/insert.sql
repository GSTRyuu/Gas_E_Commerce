USE SE1706_SWP391_Group6

INSERT INTO [Country]([name],[tax],[shipping])
VALUES
('Vietnam', 10, 0),
('China', 10, 10),
('America',5,50)

INSERT INTO [Role]([role])
VALUES
('Admin'),
('OrderManage'),
('ProductManage'),
('NewsManage')

INSERT INTO [Admin]([roleId],[firstName],[lastName],[mobile],[email],
[passwordHash], [lastLogin])
VALUES(1 ,'Duc Hieu','Do', 0946401658, 'dduchieu793@gmail.com',
'B24331B1A138CDE62AA1F679164FC62F', GETDATE())

INSERT INTO [Category] ([name], [description])
VALUES
('Gas tank', 'none'),
('Gas stove', 'none'),
('Accessories', 'none')

INSERT INTO [Product]([categoryId],[name],[model],[description],[warranty],
[sellPrice],[manufacturer],[createdAt])
VALUES
(1,'S Gas Orange','GAS120069','Gas Type: S Gas Green 12Kg.<br>
Bottle material: Steel shell is manufactured according to international standards.<br>
Products have to buy Fire and Explosion Insurance according to State regulations.',1,444000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(1,'S Gas Red','GAS120070','Gas Type: S Gas Green 12Kg.<br>
Bottle material: Steel shell is manufactured according to international standards.<br>
Products have to buy Fire and Explosion Insurance according to State regulations.',1,464000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(1,'S Gas Yellow','GAS120071','Gas Type: S Gas Green 12Kg.<br>
Bottle material: Steel shell is manufactured according to international standards.<br>
Products have to buy Fire and Explosion Insurance according to State regulations.',1,464000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(1,'S Gas Blue','GAS120072','Gas Type: S Gas Green 12Kg.<br>
Bottle material: Steel shell is manufactured according to international standards.<br>
Products have to buy Fire and Explosion Insurance according to State regulations.',1,464000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(1,'S Gas Turquoise','GAS120073','Gas Type: S Gas Green 12Kg.<br>
Bottle material: Steel shell is manufactured according to international standards.<br>
Products have to buy Fire and Explosion Insurance according to State regulations.',1,464000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(1,'S Gas Dark Blue','GAS120074','Gas Type: S Gas Green 12Kg.<br>
Bottle material: Steel shell is manufactured according to international standards.<br>
Products have to buy Fire and Explosion Insurance according to State regulations.',1,464000,
'Southern Petroleum Joint Stock Company',GETDATE()),

(2,'Kangaroo Double Gas Stove KG516M','KG516M',NULL,1,690000,
'Kangaroo',GETDATE()),
(2,'Kangaroo Double Gas Stove KG8G1C','KG8G1C',NULL,1,714000,
'Kangaroo',GETDATE()),
(2,'Sakura Double Gas Stove SA-695SG','SA-695SG',NULL,1,760000,
'Sakura',GETDATE()),
(2,'Sunhouse Double Gas Stove SHB 201MT','SHB 201MT',NULL,1,690000,
'Sunhouse',GETDATE()),
(2,'Kangaroo Double Gas Stove KG8G1A','KG8G1A',NULL,1,680000,
'Kangaroo',GETDATE()),
(2,'Duxton Double Gas Stove DG-368','DG-368',NULL,1,699000,
'Duxton',GETDATE()),

(3,'Gas flow meter','CN008',NULL,1,500000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(3,'Gas pressure gauge','CN009',NULL,1,500000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(3,'Safety valve','CN016',NULL,1,300000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(3,'Pressure regulator valve','CN022',NULL,1,300000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(3,'Solenoid valve','CN021',NULL,1,400000,
'Southern Petroleum Joint Stock Company',GETDATE()),
(3,'Level 1 pressure regulator valve','CN023',NULL,1,400000,
'Southern Petroleum Joint Stock Company',GETDATE())


INSERT INTO [ProductImage] (productId, [source])
VALUES
(1, 'images/Products/S_Gas_Orange.png'),
(2, 'images/Products/S_Gas_Red.png'),
(3, 'images/Products/S_Gas_Yellow.png'),
(4, 'images/Products/S_Gas_Blue.png'),
(5, 'images/Products/S_Gas_Turquoise.png'),
(6, 'images/Products/S_Gas_DarkBlue.png'),
(7, 'images/Products/kangaroo_1.jpg'),
(8, 'images/Products/kangaroo_2.jpg'),
(9, 'images/Products/sakura_1.jpg'),
(10, 'images/Products/sunhouse_1.jpg'),
(11, 'images/Products/kangaroo_3.jpg'),
(12, 'images/Products/duxton_1.jpg'),
(13, 'images/Products/do_luu_luong_gas.png'),
(14, 'images/Products/do_ap_suat_gas.png'),
(15, 'images/Products/van_an_toan.png'),
(16, 'images/Products/van_dieu_ap.png'),
(17, 'images/Products/van_solenoid.png'),
(18, 'images/Products/van_dieu_ap_cap_1.png')

INSERT INTO [Status]([id],[name],[content])
VALUES
(0,'on cart','containing items added as a cart'),
(1,'on checkout','containing items and user data to check out'),
(2,'pending','waiting for purchase to be accepted'),
(3,'rejected','purchase is rejected'),
(4,'purchased','purchase is accepted'),
(5,'canceled','order is canceled by user'),
(6,'on delivery','order is being delivered'),
(7,'delivered','order is delivered'),
(8,'returned/refund','order is returned by user'),
(20,'requesting','service requested'),
(21,'in progress','service in progress'),
(22,'rejected','service rejected'),
(23,'completed','service completed')

INSERT INTO [ProductInStock]([serial],[productId],[mfgDate])
VALUES
('SGO001',1,GETDATE()),
('SGO002',1,GETDATE()),
('SGO003',1,GETDATE()),
('SGO004',1,GETDATE()),
('SGO005',1,GETDATE()),
('SGR001',2,GETDATE()),
('SGR002',2,GETDATE()),
('SGR003',2,GETDATE()),
('SGR004',2,GETDATE()),
('SGR005',2,GETDATE()),
('SGY001',3,GETDATE()),
('SGY002',3,GETDATE()),
('SGY003',3,GETDATE()),
('SGY004',3,GETDATE()),
('SGY005',3,GETDATE()),
('SGB001',4,GETDATE()),
('SGB002',4,GETDATE()),
('SGB003',4,GETDATE()),
('SGB004',4,GETDATE()),
('SGB005',4,GETDATE()),
('SGT001',5,GETDATE()),
('SGT002',5,GETDATE()),
('SGT003',5,GETDATE()),
('SGT004',5,GETDATE()),
('SGT005',5,GETDATE()),
('SGDB001',6,GETDATE()),
('SGDB002',6,GETDATE()),
('SGDB003',6,GETDATE()),
('SGDB004',6,GETDATE()),
('SGDB005',6,GETDATE()),
('KG516M001',7,GETDATE()),
('KG516M002',7,GETDATE()),
('KG516M003',7,GETDATE()),
('KG516M004',7,GETDATE()),
('KG516M005',7,GETDATE()),
('KG8G1C001',8,GETDATE()),
('KG8G1C002',8,GETDATE()),
('KG8G1C003',8,GETDATE()),
('KG8G1C004',8,GETDATE()),
('KG8G1C005',8,GETDATE()),
('SA695SG001',9,GETDATE()),
('SA695SG002',9,GETDATE()),
('SA695SG003',9,GETDATE()),
('SA695SG004',9,GETDATE()),
('SA695SG005',9,GETDATE()),
('SHB201MT001',10,GETDATE()),
('SHB201MT002',10,GETDATE()),
('SHB201MT003',10,GETDATE()),
('SHB201MT004',10,GETDATE()),
('SHB201MT005',10,GETDATE()),
('KG8G1A001',11,GETDATE()),
('KG8G1A002',11,GETDATE()),
('KG8G1A003',11,GETDATE()),
('KG8G1A004',11,GETDATE()),
('KG8G1A005',11,GETDATE()),
('DG368001',12,GETDATE()),
('DG368002',12,GETDATE()),
('DG368003',12,GETDATE()),
('DG368004',12,GETDATE()),
('DG368005',12,GETDATE()),
('CN008001',13,GETDATE()),
('CN008002',13,GETDATE()),
('CN008003',13,GETDATE()),
('CN008004',13,GETDATE()),
('CN008005',13,GETDATE()),
('CN009001',14,GETDATE()),
('CN009002',14,GETDATE()),
('CN009003',14,GETDATE()),
('CN009004',14,GETDATE()),
('CN009005',14,GETDATE()),
('CN016001',15,GETDATE()),
('CN016002',15,GETDATE()),
('CN016003',15,GETDATE()),
('CN016004',15,GETDATE()),
('CN016005',15,GETDATE()),
('CN022001',16,GETDATE()),
('CN022002',16,GETDATE()),
('CN022003',16,GETDATE()),
('CN022004',16,GETDATE()),
('CN022005',16,GETDATE()),
('CN021001',17,GETDATE()),
('CN021002',17,GETDATE()),
('CN021003',17,GETDATE()),
('CN021004',17,GETDATE()),
('CN021005',17,GETDATE()),
('CN023001',18,GETDATE()),
('CN023002',18,GETDATE()),
('CN023003',18,GETDATE()),
('CN023004',18,GETDATE()),
('CN023005',18,GETDATE())

INSERT INTO [NewsGroup]([name],[type])
VALUES
('navbar','element'),
('footerContent','element'),
('banner','element'),
('Policies','footlink'),
('About Us','footlink'),
('orderSort','element'),
('orderFilter','element'),
('Sales & Promotion','news'),
('Tips & Tricks','news'),
('Review & Advise','news'),
('Event','news'),
('Academic','news'),
('Policy', 'news'),
('newsSort','element'),
('discountFilter','element')

INSERT INTO [News]([groupId],[title],[content])
VALUES (6, 'default', 'Default'), 
(6, 'grandTotal DESC', 'Total high to low'),
(6, 'grandTotal ASC', 'Total low to high'),
(6, 'createdAt DESC', 'Newest to oldest'),
(6, 'createdAt ASC', 'Oldest to newest'),
(7,'all', 'All'),
(7,'0', 'On cart'),
(7,'1', 'On checkout'),
(7,'2', 'Pending'),
(7,'3', 'Rejected'),
(7,'4', 'Purchased'),
(7,'5', 'Canceled'),
(7,'6', 'On delivery'),
(7,'7', 'Delivered'),
(7,'8', 'Returned/refund'),
(14,'Latest', 'n.createdAt'),
(14,'Most viewed', 'n.[view]'),
(15,'Discount for user','User'),
(15,'Discount for product','Product')

INSERT INTO [News]([groupId], [title], [STT], [image], [content], [link])
VALUES
(1,'Home', 1, '', '', 'home'),
(1,'Product', 2, '', '', 'shop'),
(1,'Cart', 3, '', '', 'order/cart'),
(1,'Purchase History', 4, '', '', 'order/history' ),
(1,'News', 5, '', '', 'news'),
(1,'Service', 6, '', '', 'service' ),
(2,'eGasCommerce', 1, '', 'By following these steps, you can conduct a systematic analysis of the clubs system interface, identify areas for improvement, and develop strategies to enhance the clubs functioning within the school ecosystem.', 'home' ),
(3,'Map Stakeholder Interactions', 1, 'https://duhocvietglobal.com/wp-content/uploads/2018/12/dat-nuoc-va-con-nguoi-anh-quoc.jpg', 'Visualize the interactions between the club and its stakeholders. Identify the relationships, dependencies, and communication channels between the club and each stakeholder. This step helps you understand the dynamics of the club', 'home'),
(3,'Nham Manh Hung', 2, 'https://cdn2.cellphones.com.vn/x,webp,q100/media/wysiwyg/May-anh/may-anh-1.jpg', 'Visualize the interactions between the club and its stakeholders. Identify the relationships, dependencies, and communication channels between the club and each stakeholder. This step helps you understand the dynamics of the club', 'home'),
(3,'Nham Huong Giang', 3, 'https://vuongquocanh.com/wp-content/uploads/2018/04/london.jpg', 'Visualize the interactions between the club and its stakeholders. Identify the relationships, dependencies, and communication channels between the club and each stakeholder. This step helps you understand the dynamics of the club', 'home'),
(4,'Free shipping', 1, '', '', 'policy?title=free-shipping'),
(4,'30 days return', 1, '', '', 'policy?title=30-days-return'),
(4,'Payment', 1, '', '', 'policy?title=secure-payments'),
(4,'Products', 1, '', '', 'policy?title=new-products'),
(5,'What is eGasCommerce?', 1, '', '', 'policy?title=introduce'),
(5,'How we do?', 1, '', '', 'policy?title=working-process')
			

INSERT INTO [News]([adminId],[groupId],[title],[heading],[author],
[image],[createdAt],[updatedAt],[content],[link])
VALUES(1, 10, 'Energy stocks were due for Friday’s rally. Here’s how we’re approaching our oil-and-gas names now', 'Coterra Energy’s earnings beat, free cash flow keep us investors in the oil-and-gas play', 'Catilyn Kai', 'https://image.cnbcfm.com/api/v1/image/106832268-16119236772021-01-28t222511z_33861026_rc2ahl9qrblh_rtrmadp_0_usa-biden-climate-energy.jpeg?v=1683239126&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'Western Alliance Bancorp
 — Western Alliance shares jumped 2.7% after Bank of America reinstated coverage on the stock with a buy rating. Bank of America said it is confident in the regional bank’s business model. The firm said that “WAL does not share a ton in terms of business model and balance sheet characteristics relative to the three failed banks,” noting its above-average ratio of insured deposits to total deposits. Shares are down 46% year to date.', 'fixed')

INSERT INTO [News]([adminId],[groupId],[title],[heading],[author],
[image],[createdAt],[updatedAt],[content])
	VALUES (1, 12, 'Oil remains in range… but has natural gas slide bottomed out?', 'In many parts of China, businesses were scheduled to resume work this past Monday, but a variety of data indicates progress has been slow as the virus remains an unresolved concern.', 'Jared Wick', 'https://image.cnbcfm.com/api/v1/image/107157514-1669639612750-gettyimages-1245162111-AA_28112022_961726.jpeg?v=1683315681&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'BEIJING – Two weeks after the Lunar New Year holiday was originally supposed to end, Chinese businesses are still getting up to speed as the country deals with disruptions from a highly contagious virus.
The new coronavirus that began to grab national attention in mid-January has killed more than 1,300 people in mainland China. More than half of the provinces delayed the resumption of work from the first week of February by at least a week in an effort to keep people from interacting and spreading the virus.'),
	(1, 8, 'FULL GAS', 'Offer available from 26/04/2023 on selected GASGAS MY23 Motocross, Enduro and Cross Country Models. Offer only available for a limited time only and while stocks last. Only available at participating authorised dealers.', 'Admin', 'https://s7g10.scene7.com/is/image/ktm/MY22-23_GG_Red-Hot_720x720%20(1)?$ktm_teaser_square$&wid=500&dpr=off', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', '*Credit provided by BOQ Credit Pty Limited ABN 92 080 151 266 (BOQC) (Australian Credit Licence Number 393331). BOQC is a wholly owned subsidiary of Bank of Queensland Limited ABN 32 009 656 740 (BOQ). BOQ does not guarantee or otherwise support the obligations or performance of BOQC or the products it offers. BOQC standard credit assessment criteria apply and fees and charges are payable. The interest rate is 1.99%pa and applicable on a 48 month term, with a 10% deposit on a consumer loan agreement.
2.    The comparison rate of 3.28%pa is calculated on a loan amount of $30,000 for a term of 5 years. These rates are for secured loans only. WARNING: This comparison rate is true only for the examples given and may not include all fees and charges. Different terms, fees or other loan amounts might result in a different comparison rate. 
3.    Offer available on Select New GASGAS motorcycles that are purchased between 20th March 2023 and 30th June 2023 (inclusive) that settle by 31st July 2023. Full terms and conditions available at authorised, participating GASGAS Motorcycles dealerships.'),
	(1, 8, 'Red Hot', 'Offer available from 24/04/2023 on selected GASGAS MY22 & MY23 Street Models. Offer only available for a limited time only and while stocks last. Only available at participating authorised dealers.', 'Admin', 'https://s7g10.scene7.com/is/image/ktm/MY23_GG_Full-Gas_720x720-2?$ktm_teaser_square$&wid=500&dpr=off', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'Offer available on Select New GASGAS motorcycles that are purchased between 20th March 2023 and 30th June 2023 (inclusive) that settle by 31st July 2023. Full terms and conditions available at authorised, participating GASGAS Motorcycles dealerships.'),
	(1, 11, 'Charm 53 million to turn corn leftovers into oil and inject it into abandoned oil and gas wells', 'Charm sequesters carbon dioxide underground by gathering excess organic material — like corn stover — and converting that into a bio-oil, which it then pumps into abandoned oil and gas wells.', 'Peter Reinhardt', 'https://image.cnbcfm.com/api/v1/image/107242921-1684354954216-2022__Pyrolyzer_Apatosaurus_in_Kansas_with_Team.jpg?v=1684411201&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'The $53 million contract with Charm is different because it is exponentially larger than previous agreements, and because it’s the first offtake agreement Frontier has inked, which is a legally binding contract paid out as the tons of carbon removal are delivered and sequestered. The 111,000 tons of carbon dioxide that Charm will remove is more than ten times the total quantity of carbon dioxide that has been removed so far with human techniques. It has already removed 6,160 tons.
Frontier picked Charm as its first large-scale carbon removal company because of Charm’s ability to deliver results.
“The field over the past couple of years has grown pretty quickly and is getting a lot of attention, but ultimately, carbon removal really still is in its infancy,” Nan Ransohoff, the head of Frontier, told CNBC. “Charm went from concept to delivering thousands of tons in effectively less than three years. I hope that we see many more carbon removal companies with this trajectory.”'),
	(1, 12, 'New York poised to pass first statewide law banning natural gas in new buildings', 'The law would likely take effect in 2026 for most new buildings under seven stories and in 2029 for larger buildings.', 'Kathy Hochul', 'https://image.cnbcfm.com/api/v1/image/107234069-1683040797876-gettyimages-1484697802-mg_2383_sunset02_04232023.jpeg?v=1683982801&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'New York is poised to become the first state in the country to pass a law banning fossil fuel combustion in most new buildings, getting rid of gas stoves, furnaces and propane heating in favor of climate-friendly appliances such as heat pumps and induction stoves.
The law would likely take effect in 2026 for most new buildings under seven stories and in 2029 for larger buildings. Following weeks of negotiations, Gov. Kathy Hochul and state lawmakers included the ban in the $229 billion state budget deal, with a final vote to enact the law anticipated this week. '),
	(1, 10, 'This big oil and nat gas producer is prioritizing stock repurchases. So we’re buying alongside it', 'But EVs may be a better financial deal for consumers over the long haul. That’s because maintenance, repair and fuel costs tend to be lower than those for gas cars.', 'Debapriya Chakraborty', 'https://image.cnbcfm.com/api/v1/image/107200471-1677528554272-NUP_200782_00284.jpg?v=1683128694&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'Since that study was published, many EVs have gotten cheaper and conventional vehicles more expensive, said Chris Harto, senior transportation and energy policy analyst at Consumer Reports.
The Inflation Reduction Act, which President Joe Biden signed in August, extended a federal tax credit for new EVs through 2032. That tax incentive — which is worth up to $7,500 and carries some qualification restrictions — aims to make EVs more affordable.'),
	(1, 12, 'Are gas-powered or electric vehicles a better deal? EVs may win out in long run, experts say', 'Electric vehicles have a higher sticker price than traditional gasoline-powered cars, on average.', 'Michael Wayland', 'https://image.cnbcfm.com/api/v1/image/107225716-1681498305205-Avenir.PNG?v=1681736401&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'To illustrate the caveats, Woody — who is conducting a study of regional lifecycle costs of gasoline versus electric vehicles — offered an example of hypothetical car buyers in Chicago and Houston.
He compared the total lifetime cost in each city for a small electric sport utility vehicle with a 300-mile range and a $48,000 suggested retail price to that of a small gas-powered SUV with a $31,000 price tag.
In Chicago, an average buyer would come out ahead with the electric over 15 years. They would pay about $84,000 total, versus $87,000 for the gas car, Woody said.'),
	(1, 10, 'GM reveals Buick Envista as brand’s last new gas-powered vehicle', 'The 2024 Buick Envista will be the brand’s last new gas-powered vehicle ahead of its transition to an all-electric domestic lineup by 2030.', 'Jim Cramer', 'https://image.cnbcfm.com/api/v1/image/107229151-1682078812107-gettyimages-1448745796-3913.jpeg?v=1682256601&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'Here’s why: Buyers in Chicago can get an extra $4,000 incentive from the state, making EVs less expensive at the time of purchase, Woody said. Chicago also has relatively inexpensive electricity, so the EV is also much less costly to operate, he added.
On the other hand, Houston has among the lowest gas prices in the country, reducing the overall fuel-cost savings reaped from an EV when compared with a traditional car. Texas also doesn’t offer an additional tax incentive to EV buyers.
The analysis accounts for cooler weather in Chicago, which generally makes EVs less efficient, Woody said.'),
	(1, 9, 'Europe markets close 1.2% lower as Fed meeting kicks off; oil and gas stocks down 4.5%', 'European equity markets tumbled on Tuesday as a fall in oil prices drove energy stocks lower and investors braced for an expected Federal Reserve rate hike.', 'Holly Ellyatt', 'https://image.cnbcfm.com/api/v1/image/107218989-1680444982474-gettyimages-1247723764-1262435-me-snowed-in-san-bernardino-mountains-42-brv.jpeg?v=1680445584&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'Markets also reacted to BP
’s first-quarter results. Profits were lower than 2022 exceptional levels when fossil fuel prices soared in response to Russia’s full-scale invasion of Ukraine.
Shares of the energy giant fell as much as 8.6% following the earnings report, which included its expectation of share buybacks of around $4 billion per year, at the lower end of its $14 billion to $18 billion capital expenditure range.
Banking stocks were positive through most of the session following JP Morgan’s takeover of First Republic and a slew of European earnings last week. However, they ended the session down 1.5%, following U.S. bank shares'),
	(1, 12, 'Gas prices increase in N.J., around U.S. amid higher demand', 'Nevada’s largest electricity provider has been approved for a $333 million project to develop a natural gas plant north of Las Vegas.', 'Sam Meredith', 'https://image.cnbcfm.com/api/v1/image/107214139-1679587763240-gettyimages-106796566-59b5c22e-3aac-4593-a88c-617c040bd83b.jpeg?v=1679592069&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'British energy giant BP posted stronger-than-anticipated first-quarter earnings on robust oil and gas trading. Its results rose from the previous three months but were down from the exceptional levels seen throughout a blockbuster 2022. The oil major posted first-quarter underlying replacement cost profit, used as a proxy for net profit, of $4.96 billion.
That compared with a profit of $4.8 billion in the fourth quarter and $6.2 billion for the first quarter of 2022. Analysts had expected BP to report first-quarter profit of $4.3 billion, according to Refinitiv.
“This has been a quarter of strong performance and strategic delivery as we continue to focus on safe and reliable operations,” BP CEO Bernard Looney said.'),
	(1, 11, 'Nevada approves 333 million natural gas plant as historic drought pressures state’s power grid', 'The project’s turbines are designed to address peak electricity demand during hot summer months and prolonged wildfire seasons.', 'Emma Newburger', 'https://image.cnbcfm.com/api/v1/image/107214138-1679587646824-gettyimages-1399269041-grose54527.jpeg?v=1679592069&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'The Western U.S is in the midst of a historic megadrought, depleting water levels at the Colorado River’s reservoirs, Lake Powell and Lake Mead. Declining reservoir supply has prompted concerns over the future of hydroelectric power generation at the river’s Hoover Dam and Glen Canyon Dam.
Natural gas presents its own challenges. Some environmental groups have argued that a new plant will jeopardize Nevada’s climate and clean energy agenda. The state has committed to a carbon-free power grid by 2050 and hasn’t built a new natural gas plant in over a decade.
More than two-thirds of Nevada’s electricity is produced by natural gas-fired power plants, while renewables comprise most of the rest, according to the state’s energy report.'),
	(1, 12, 'Norway faces backlash from campaigners for ‘reckless’ pursuit of Arctic oil and gas', 'A spokesperson for Norway’s petroleum and energy ministry told CNBC that the message to energy giants was “to explore all economic oil and gas resources within the available areas, including in the Barents Sea.”', 'Sam Meredith', 'https://image.cnbcfm.com/api/v1/image/107243875-1684495882176-gettyimages-1242451518-AA_12082022_825806.jpeg?v=1684732162&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'Aasland even described this policy as the oil and gas industry’s “social responsibility,” according to Bloomberg, saying undiscovered resources could help to maintain the country’s future production levels.
Norway oil and gas giant Equinor and Vår Energi, one of the country’s largest exploration and production companies, confirmed to CNBC that the minister recently issued this call.
A spokesperson for Norway’s petroleum and energy ministry, meanwhile, said that the message to energy giants was “to explore all economic oil and gas resources within the available areas, including in the Barents Sea.”
Norway has pumped oil and gas from its continental shelf, a relatively shallow section of seabed off its coast, for more than 50 years and it currently h'),
	(1, 12, 'Situation around Europe’s largest nuclear power plant is ‘potentially dangerous,’ watchdog warns', 'International Atomic Energy Agency Director-General Rafael Mariano Grossi said in a statement that he was “extremely concerned about the very real nuclear safety and security risk,” facing the Zaporizhzhia power plant in southeast Ukraine.', 'Leila Sackur', 'https://image.cnbcfm.com/api/v1/image/107217163-1680105327036-gettyimages-1249892275-AFP_33CA6TQ.jpeg?v=1683712927&w=740&h=416&ffmt=webp&vtcrop=y', '2023-05-17 10:30:00.000', '2023-05-17 10:30:00.000', 'The statement said that the IAEA had received information that residents from the nearby town of Enerhodar, where most of the plant staff live, had started and the organization was “closely monitoring the situation for any potential impact on nuclear safety and security.”
“The IAEA experts at the site are continuing to hear shelling on a regular basis,” the statement added.
Dmytro Orlov, the exiled mayor of Enerhodar, said in a Telegram post on Saturday that Russian forces were fuelling an atmosphere of “panic” with recent announcements of evacuations.
 “The first wave of evacuations began yesterday morning,but it cannot be called ‘mass’ now. Some of the people who wanted to leave were loaded onto buses. Some left in their own vehicles. Accordingly, gas stations ran out of fuel yesterday. ATMs do not work, or work with great restrictions, and there is nowhere to withdraw money,” he said.')

INSERT INTO [News]([adminId],[groupId],[title],[createdAt],[content])
	VALUES (1, 13, '30 Days return', '2023-07-22 10:46:43.000', '<header class="entry-header" style="box-sizing: inherit; min-height: 0px; min-width: 0px; color: #323232; font-family: Poppins, sans-serif; font-size: 16px; background-color: #ffffff;">
<h2 style="box-sizing: inherit; min-height: 0px; min-width: 0px; font-size: 1.953em; overflow-wrap: break-word; font-weight: 400; line-height: 1.2; margin: 0px 0px 12px;">Return</h2>
</header>
<div class="entry-content" style="box-sizing: inherit; min-height: 0px; min-width: 0px; color: #323232; font-family: Poppins, sans-serif; font-size: 16px; background-color: #ffffff;">
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">If for any reason you are unhappy with your purchase you may return items complete, unused and in their original packing within 30 days of receiving them for an exchange or refund.</p>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">If 30 days have gone by since your purchase, unfortunately, we can&rsquo;t offer you a refund or exchange. Returns after 30 days will only be accepted on faulty goods.</p>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">To be eligible for a return, your item must be unused and in the same condition that you received it. It must also be in the original packaging. We don&rsquo;t reimburse the cost of postal returns unless the goods are faulty. When returning items, please obtain proof of postage from your Post Office; we also recommend that overseas customers use a registered trackable service and get a proof of postage receipt.</p>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">To complete your return, please contact us with your order details.</p>
<h2 style="box-sizing: inherit; min-height: 0px; min-width: 0px; font-size: 1.953em; overflow-wrap: break-word; font-weight: 400; line-height: 1.2; margin: 0px 0px 12px;">Refunds (if applicable)</h2>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">Once your return is received and inspected, we will send you an email to notify you that we have received your returned item. We will also notify you of the approval or rejection of your refund.<br style="box-sizing: inherit; min-height: 0px; min-width: 0px;" />If you are approved, then your refund will be processed, and a credit will automatically be applied to your credit card or original method of payment, within a certain amount of days.</p>
<h2 style="box-sizing: inherit; min-height: 0px; min-width: 0px; font-size: 1.953em; overflow-wrap: break-word; font-weight: 400; line-height: 1.2; margin: 0px 0px 12px;">Late or missing refunds (if applicable)</h2>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">If you haven&rsquo;t received a refund yet, first check your bank account again.<br style="box-sizing: inherit; min-height: 0px; min-width: 0px;" />Then contact your credit card company, it may take some time before your refund is officially posted.<br style="box-sizing: inherit; min-height: 0px; min-width: 0px;" />Next, contact your bank. There is often some processing time before a refund is posted.</p>
<h2 style="box-sizing: inherit; min-height: 0px; min-width: 0px; font-size: 1.953em; overflow-wrap: break-word; font-weight: 400; line-height: 1.2; margin: 0px 0px 12px;">Shipping</h2>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">To return your product, you should mail your product to our official website</p>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">You will be responsible for paying for your own shipping costs for returning your item. Shipping costs are non-refundable.</p>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">Depending on where you live, the time it may take for your exchanged product to reach you may vary.</p>
<p style="box-sizing: inherit; min-height: 0px; min-width: 0px; margin: 0px 0px 24px; padding: 0px;">&nbsp;</p>
</div>'),
			(1, 13, 'Free shipping', '2023-07-22 10:46:43.000', '<p>Pre-tax orders or more qualify for free Front Door Delivery.</p>
<p>Please note that if you live outside our standard service area, additional fees may apply. In that case, youll receive a discount on any additional fees quoted at checkout. This discount can be applied to both In-Room and In-Room + Assembly Delivery if you select either of those options. With the discount, the In-Room Delivery fee will be for In-Room + Assembly Delivery.</p>
<p>The free shipping offer for orders is not applicable to Article for Trade or Article for Business purchases. For these orders, regular rates apply.</p>'),
			(1, 13, 'Secure payments', '2023-07-22 10:46:43.000', '<p>At our website, we take the security of our customers'' information seriously. That''s why we have implemented a strict policy of secure payment options for all transactions.</p>
<p>We use advanced encryption technology to protect customer information during transactions, ensuring that it is unreadable to anyone who does not have the key to unlock it. Additionally, we only use trusted and reliable payment gateways that are equipped with the latest security features to verify transactions and process payments.</p>
<p>We also implement other security measures, such as two-factor authentication and fraud detection software, to further protect our customers'' sensitive information. Our commitment to secure payment options helps to build trust with our customers and ensures that their personal and financial information is always kept safe and secure.</p>
<p>We believe that this commitment to security is essential to providing our customers with the best possible online shopping experience.</p>'),
			(1, 13, 'New products', '2023-07-22 10:46:43.000', '<p>New products can be daunting for customers, which is why we''ve implemented some clear policies to ensure that our customers have a positive experience:</p>
<p><br />We''ve made sure that our product descriptions are detailed and accurate. We want our customers to have all the information they need to make informed purchasing decisions, so we''ve included information about the product''s features, dimensions, materials, and any other relevant information. This ensures that our customers know exactly what they''re getting before they make a purchase.</p>
<p>In addition to detailed product descriptions, we''ve also made sure that our product images are high-quality and provide a clear representation of the product. We know that customers rely on product images to get a sense of what a product looks like, so we''ve made sure to include multiple images from different angles.</p>
<p>We''ve also made our pricing clear and transparent. Customers can expect to see the price of the product, including any applicable taxes, shipping fees, and other charges. This ensures that there are no surprises when it comes to the total cost of the purchase.</p>
<p>Finally, we''ve implemented a fair return policy for our new products. Customers can return the product within a reasonable timeframe if they are not satisfied, provided that the product is in its original condition. We''ve also specified any conditions for returns, such as the condition of the product and any restocking fees that may apply.</p>
<p>We hope that these policies help our customers feel confident and informed when purchasing new products from our website. As always, we''re committed to providing the best possible shopping experience for our customers.</p>'),
			(1, 13, 'Working process', '2023-07-22 10:46:43.000', '<p>Welcome to our gas-selling website! We are proud to offer a wide range of high-quality gas products and services to meet the needs of our customers. In this article, we will introduce you to our working process, so you can understand how we provide our products and services.</p>
<p>1. Product selection: We carefully select our gas products from reliable sources to ensure their quality. Our products include various types of gas for household and industrial use, such as propane, butane, and natural gas.</p>
<p>2. Online ordering: Our website is designed to make the ordering process easy and convenient for our customers. You can browse our product catalogue, select the products you need, and place your order online. Our website is also mobile-friendly, so you can order from anywhere using your smartphone or tablet.</p>
<p>3. Secure payment: We offer secure online payment options for our customers, so you can pay for your order with confidence. Our website is also equipped with SSL encryption to protect your personal and payment information.</p>
<p>4. Fast delivery: We understand that timely delivery is important to our customers, so we work hard to ensure fast and efficient delivery of our products. Our delivery team is experienced and reliable, and they will make sure your order is delivered to your doorstep on time.</p>
<p>5. Installation and maintenance: Our services also include gas installation and maintenance for households and industrial customers. Our team of experts is trained and equipped to provide safe and professional gas installation services, and we also offer regular maintenance services to ensure the safe and efficient use of your gas products.</p>
<p>6. Customer support: We take pride in providing excellent customer support to our customers. Our customer service team is available to assist you with any questions or concerns you may have, and we are committed to resolving any issues you may encounter.</p>
<p>In summary, we offer a comprehensive range of gas products and services, including product selection, online ordering, secure payment, fast delivery, installation and maintenance, and customer support. We strive to provide our customers with the best possible experience, and we are always looking for ways to improve our products and services. Thank you for choosing our gas-selling website, and we look forward to serving you!</p>'),
			(1, 13, 'Introduce', '2023-07-22 10:46:43.000', '<p>Welcome to eGasCommerce, your go-to destination for high-quality gas products and services. Our website is dedicated to providing a seamless and reliable platform for purchasing gas products online.</p>
<p>At eGasCommerce, we prioritize the safety and quality of our gas products. We carefully select our products from trusted suppliers to ensure that our customers receive only the best. From propane to natural gas, our extensive product catalogue caters to the needs of both households and industrial customers.</p>
<p>Our website is designed to make the ordering process simple and streamlined. Our user-friendly interface allows for easy browsing and selection of products, while our secure online payment options provide peace of mind.</p>
<p>At eGasCommerce, we are committed to providing exceptional customer service. Our team of experts is available to answer your questions and provide support throughout the ordering and delivery process. We also offer gas installation and maintenance services to ensure the safe and efficient use of our products.</p>
<p>Thank you for choosing eGasCommerce as your trusted source for high-quality gas products and services. We strive to exceed your expectations and provide a hassle-free experience every time you shop with us.</p>')

INSERT INTO [Service] ([code],[type],[name],[amount],[price],[status],[content],[createdAt],[updatedAt])
VALUES('BASE1',NULL,NULL,0,0,'activated',NULL,NULL,NULL),
('BASE2',NULL,NULL,0,0,'inactivated',NULL,NULL,NULL),
('EXT001','extend','Extend warranty period by 1 year',1,1500000,'inactivated','Add 1 year to current warranty duration.',GETDATE(),NULL),
('EXT002','extend','Extend warranty period by 2 year',2,2000000,'inactivated','Add 2 year to current warranty duration.',GETDATE(),NULL),
('EXC001','exchange','Exchange accessories',0,500000,'inactivated','Exchange a part of current product.',GETDATE(),NULL),
('REP001','replace','Replace accessories',0,500000,'inactivated','Replace a broken part of current product.',GETDATE(),NULL),
('RPR001','repair','Repair accessories',0,500000,'inactivated','Repair a broken part of current product.',GETDATE(),NULL)