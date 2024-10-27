1. Explain app structure:
   init -> get api (if fail try again) -> put data in list -> create listener -> calculate when change and set new value -> after 60 minutes get api again
2. Open Android Studio -> check if build.gradle :app need to sync -> run app
3. Note: Drop down menu still not optimize; a big currency if input max limit length will delete number of smaller currency; small currency if too small will make big curency become 0
4. Challenge encounted: Component need to listen, more difficult than when use onChange like web; because of limited time of api used, didn't test too much
5. Link video: https://drive.google.com/file/d/18LYF5adZdus3eRlmCW8TEv0qxGl4aeio/view?usp=sharing (content include: what happen if change text and spinner, how about select a item same with other spinner)  
If you want to test the error first, please turn off wifi because it takes 1 hour to get api again   
