
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// generated by gradle task
package org.apache.sqoop;

public class SqoopVersion {
 public SqoopVersion() {
 }

 public static final String VERSION="1.5.0-yzp";
 public static final String GIT_HASH="354eda5e65db35f28322043015c4dc2facb119c5";
 public static final String COMPILE_USER="yangzhenpeng";
 public static final String COMPILE_DATE="13-04-2020 09:08:20";

 @Override
 public String toString() {
   return "Sqoop " + VERSION + "\n"
   + "git commit id " + GIT_HASH + "\n"
   + "Compiled by " + COMPILE_USER
   + " on " + COMPILE_DATE + "\n";
 }
   }
