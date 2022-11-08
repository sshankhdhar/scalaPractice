package exercise
import java.io.File

import scala.io
import scala.io.Source
object CompareJsonFiles extends App {

  def getListOfJsonFiles(dir: String): Set[String] = {
    val file = new File(dir)
    file.listFiles
      .map(_.getName)
      .toSet
  }

  def getListOfJsonFilePaths(dir: String): Set[String] = {
    val file = new File(dir)
    file.listFiles
      .map(_.getPath)
      .toSet
  }

  def getJsonContent(dir: String) = {
    for (json_content <- Source.fromFile(dir).getLines) {
      println(json_content)
    }
  }

  val featureFilePath =
    getClass.getClassLoader.getResource("FeatureFile").getPath
  val partner_ConfigPath =
    getClass.getClassLoader.getResource("Partner-config").getPath

  val filesFromFeatureFileFolder = getListOfJsonFiles(featureFilePath)
  val filesFromPartner_ConfigFolder = getListOfJsonFiles(partner_ConfigPath)

  val filePathsFromFeatureFileFolder = getListOfJsonFilePaths(featureFilePath)
  val filePathsFromPartner_ConfigFolder = getListOfJsonFilePaths(
    partner_ConfigPath
  )
  println(filesFromFeatureFileFolder, filesFromPartner_ConfigFolder)
  println(filesFromFeatureFileFolder == filesFromPartner_ConfigFolder)

  getJsonContent(filePathsFromFeatureFileFolder.head)

}
