/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.integration.persistence.datasource.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DerbyDropTable
{

   public static void dropTable(String schema, String table)
   {
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:derby:target/db/derby");
         PreparedStatement ps = conn.prepareStatement("drop table " + schema + "." + table);
         ps.executeUpdate();
         conn.commit();
         ps.close();
      }
      catch (SQLException e)
      {
         // IGNORE - it fails when you try to drop non-existing table, but we want to continue
      }
      finally
      {
         if (conn != null)
         {
            try
            {
               conn.close();
            }
            catch (SQLException e)
            {
               // IGNORE
            }
         }
      }
   }

}
