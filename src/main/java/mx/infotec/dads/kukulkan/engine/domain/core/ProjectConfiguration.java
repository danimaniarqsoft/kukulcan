/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.engine.domain.core;

import java.io.Serializable;

import mx.infotec.dads.kukulkan.util.GenerationType;

/**
 * ProrjectConfiguration Class
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ProjectConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String groupId;
    private String version;
    private String packaging;
    private String author;
    private String year;
    private String appName;

    private String webLayerName;
    private String serviceLayerName;
    private String daoLayerName;
    private String domainLayerName;
    private String dtoLayerName;
    private String exceptionLayerName;
    private String conacytDaoLayerName;

    // Global Generation Types Strategy
    private GenerationType globalGenerationType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWebLayerName() {
        return webLayerName;
    }

    public void setWebLayerName(String webLayerName) {
        this.webLayerName = webLayerName;
    }

    public String getServiceLayerName() {
        return serviceLayerName;
    }

    public void setServiceLayerName(String serviceLayerName) {
        this.serviceLayerName = serviceLayerName;
    }

    public String getDaoLayerName() {
        return daoLayerName;
    }

    public void setDaoLayerName(String daoLayerName) {
        this.daoLayerName = daoLayerName;
    }

    public String getDomainLayerName() {
        return domainLayerName;
    }

    public void setDomainLayerName(String domainLayerName) {
        this.domainLayerName = domainLayerName;
    }

    public GenerationType getGlobalGenerationType() {
        return globalGenerationType;
    }

    public void setGlobalGenerationType(GenerationType globalGenerationType) {
        this.globalGenerationType = globalGenerationType;
    }

    public String getDtoLayerName() {
        return dtoLayerName;
    }

    public void setDtoLayerName(String dtoLayerName) {
        this.dtoLayerName = dtoLayerName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getExceptionLayerName() {
        return exceptionLayerName;
    }

    public void setExceptionLayerName(String exceptionLayerName) {
        this.exceptionLayerName = exceptionLayerName;
    }

    public String getConacytDaoLayerName() {
        return conacytDaoLayerName;
    }

    public void setConacytDaoLayerName(String conacytDaoLayerName) {
        this.conacytDaoLayerName = conacytDaoLayerName;
    }

}
