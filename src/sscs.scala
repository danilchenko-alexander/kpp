import scala.io.Source
import java.io._
class sscs {
  var i = 0
  var cycle = 10000

  var path = "replays/"
  var masOfFiles:Array[Long] = new Array[Long](10000)
  var files:Array[String] = new Array[String](10000)
  def sort (retSorted : Array[String])= {
    for (i <- 0 until cycle){
      retSorted(i) = "game"+i.toString()+".txt"
      val f = new File(path+retSorted(i))
      masOfFiles(i) = f.length()
    }

    for (i <- 0 until cycle-1){
      var j = cycle-2
      while (j >= i){
        if(masOfFiles(j) > masOfFiles(j+1)){
          var l = masOfFiles(j);
          var (s:String) = retSorted(j);
          masOfFiles(j) = masOfFiles(j+1);
          retSorted(j) = retSorted(j+1);
          masOfFiles(j+1) = l;
          retSorted(j+1) = s;
        }
        j-=1
      }
    }
  }
  
  def sortBomb(retSorted:Array[String]) = {
    var bombs = 0
    var i = 0
    var arr:Array[Int] = new Array[Int](cycle)
    for(i <- 0 until cycle){
      retSorted(i) = "game"+i.toString()+".txt"
      val f = new File(path+retSorted(i))
      val s = new java.util.Scanner(f)
      while (s.hasNext()){
        bombs = s.nextInt()
        if (s.hasNext())
        bombs = s.nextInt()
        if (bombs == 99)
          arr(i) +=1
      }
    }
    
    for (i <- 0 until cycle-1){
      var j = cycle-2
      while (j >= i){
        if(arr(j) > arr(j+1)){
          var l = arr(j);
          var (s:String) = retSorted(j);
          arr(j) = arr(j+1);
          retSorted(j) = retSorted(j+1);
          masOfFiles(j+1) = l;
          retSorted(j+1) = s;
        }
        j-=1
      }
    }
  }
  
  def statistic (st:Array[String]) = {
    var arr:Array[Int] = new Array[Int](3)
    var str:Array[String] = new Array[String](6)
    for (i <- 1 to 5){
      str(i) = i.toString()+"._statistic"+".txt"
      val f = new File(path+str(i))
      val s = new java.util.Scanner(f)
      for (j <- 0 until 3){
        arr(j)+=s.nextInt()
      }
    }
    
    for (i <- 0 until 3){
      arr(i) /= 5
      st(i) = arr(i).toString()
    }  
  }
  
  def writeNotation (suf:Int) = suf match {
    case 99 => Bomber.Save(99, 99, true)
    case 44 => Bomber.Save(44, 44, true)
    case 33 => Bomber.Save(33,33, true)
    case _ =>Bomber.Save(1,1, false) 
  }
}