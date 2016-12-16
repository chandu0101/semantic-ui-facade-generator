# semantic-ui-facade-generator

#### How To Run 

 1) Fix `outputPath` in `SuiLibrary.scala`
 
 2) `sbt run`
 
 
#### How to Add New Component 
 
 1) Add your component to `components` list in `SuiLibrary.scala`
 
 2) run app,if you find any matching errors then fix those props in `SuiTypeMapper.scala` , there is a `index.d.ts` file in resources folder use that as a reference.
 
 3) watchout for `any` type props in typescript def file  and map them to correct types