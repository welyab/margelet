/*
 * Copyright 2018 Welyab da Silva Paula
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.welyab.margelet;

/**
 * Main exception of Margelet.
 * 
 * @author Welyab Paula
 */
public class MargeletException extends RuntimeException {

    /**
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("javadoc")
    public MargeletException() {
    }

    @SuppressWarnings("javadoc")
    public MargeletException(String message) {
	super(message);
    }

    @SuppressWarnings("javadoc")
    public MargeletException(Throwable cause) {
	super(cause);
    }

    @SuppressWarnings("javadoc")
    public MargeletException(String message, Throwable cause) {
	super(message, cause);
    }
}
