/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.rs.xe;

import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.EqualsAndHashCode;
import org.xembly.Directive;
import org.xembly.Directives;

/**
 * Xembly source to create SLA attribute with server IP address.
 *
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id$
 * @since 0.3
 */
@EqualsAndHashCode
public final class XeLocalhost implements XeSource {

    /**
     * Attribute name.
     */
    private final transient String attr;

    /**
     * Ctor.
     */
    public XeLocalhost() {
        this("ip");
    }

    /**
     * Ctor.
     * @param name Attribute name
     */
    public XeLocalhost(final String name) {
        this.attr = name;
    }

    @Override
    public Iterable<Directive> toXembly() {
        String addr;
        try {
            addr = InetAddress.getLocalHost().getHostAddress();
        } catch (final UnknownHostException ex) {
            addr = ex.getClass().getCanonicalName();
        }
        return new Directives().attr(this.attr, addr);
    }
}
