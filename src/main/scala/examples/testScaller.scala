/*
 * Copyright (c) 2016 LIBBLE team supervised by Dr. Wu-Jun LI at Nanjing University.
 * All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package libble.examples

import libble.features.Scaller
import org.apache.spark.{SparkConf, SparkContext}

/**
  * This is an example of using Scaller.
  */
object testScaller {
  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName("myTest")
    val sc = new SparkContext(conf)

    import libble.context.implicits.sc2LibContext
    val training = sc.loadLIBBLEFile("sparse.data")

    val scaller = new Scaller(true, true)
    val features = training.map(_.features)
    scaller.computeFactor(features)

    println("center:" + scaller.getCenter.get)
    println("std:" + scaller.getStd.get)
    val result = scaller.transform(features).collect()
    println(result.mkString(", "))


  }

}
