# TwitterPicBot

## Function

This is a little bot posting periodically pictures from a list of deviant art atrwork links to your twitter account.

<img src="http://image.prntscr.com/image/7394b1559737428db2dbe196c9e08f2d.png"/>

## Installation

<img src="https://image.flaticon.com/icons/svg/188/188234.svg" width="15"/>  Download 'TwitterPicBot.jar', 'tokens.txt' and 'piclist.txt' and put these files together in a folder.
<br/><br/><img src="http://image.prntscr.com/image/66fd1df88dda4d3594fa49120c9e8358.png" width=""/>

<img src="https://image.flaticon.com/icons/svg/188/188235.svg" width="15"/>  Open the <a href="https://apps.twitter.com/" target="_blank">Twitter Application Management</a> and create a App to connect the bot with your Twitter account.

<img src="https://image.flaticon.com/icons/svg/188/188236.svg" width="15"/>  Now get your consumer and acces credentials and enter them in the file 'tokens.txt'.
<br/><br/><img src="http://image.prntscr.com/image/18d93acf2730406c81c891abf25e1ea9.png" width=""/>

<img src="https://image.flaticon.com/icons/svg/188/188237.svg" width="15"/>  Finally, start the bot in console with the following command:
```bash
java -jar TwitterPicBot.jar -start 16:00:00_12-12-2017 -interval 4
```
*`In this example, the bot will post a picture every 4 hours after 16:00 in 12th of December 2017`*

If you want to run it on a linux server, use this command:
```bash
screen -L -S picBot java -jar TwitterPicBot.jar -start 16:00:00_12-12-2017 -interval 4
```

<img src="https://image.flaticon.com/icons/svg/188/188238.svg" width="15"/>  At last, put some nice DeviantArt Artworks as links in the 'piclist.txt'. The bot will post them and will delete the last send picture link out of the list.
<br/><br/><img src="http://image.prntscr.com/image/1fb0b161e25f45d4a94cc7dd4ab0fd00.png" width=""/>

## Used libraries
- <a href="http://twitter4j.org/en/index.html">twitter4j</a>
- <a href="https://jsoup.org/">jsoup</a>
- <a href="https://github.com/mwanji/toml4j">Toml4j</a>
