/*
 * Copyright (c) 2014, Bastien Aracil
 * All rights reserved.
 * New BSD license. See http://en.wikipedia.org/wiki/Bsd_license
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *    * The name of Bastien Aracil may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL BASTIEN ARACIL BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.femtoparsec.units.generator;

import lombok.NonNull;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * @author Bastien Aracil
 */
public class VelocityDumper {

  private final VelocityEngine engine;

  private final String templateName;

  public VelocityDumper(String templateName) {
    final Properties properties = readProperties(VelocityDumper.class, "velocity.properties");
    this.engine = new VelocityEngine(properties);
    this.engine.init();
    this.templateName = templateName;
  }

  public void dump(Path outputFile, VelocityContext context) {
    try (Writer writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
      final Template template;
      template = engine.getTemplate(templateName);
      template.merge(context, writer);
    } catch (Exception e) {
      throw new RuntimeException("Error when generating file '" + outputFile + "'", e);
    }
  }

  @NonNull
  private static Properties readProperties(Class<?> clazz, String resourceName) {
    Properties properties;
    try (InputStream is = clazz.getResourceAsStream(resourceName)) {
      properties = new Properties();
      properties.load(is);
    } catch (IOException e) {
      throw new RuntimeException("Error when reading properties : " + resourceName);
    }
    return properties;
  }

}
