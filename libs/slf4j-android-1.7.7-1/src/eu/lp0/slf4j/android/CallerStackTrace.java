/**
 * Copyright 2013  Simon Arlott
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.lp0.slf4j.android;

final class CallerStackTrace extends Throwable {
	private static final long serialVersionUID = 1L;
	private static final StackTraceElement UNKNOWN = new StackTraceElement("<unknown class>", "<unknown method>", null, -1);
	private final StackTraceElement stackFrame;

	public CallerStackTrace(final int frames) {
		StackTraceElement stackFrame;
		try {
			stackFrame = getStackTrace()[frames];
		} catch (ArrayIndexOutOfBoundsException e) {
			stackFrame = UNKNOWN;
		}
		this.stackFrame = stackFrame;
	}

	public final StackTraceElement get() {
		return stackFrame;
	}

	@Override
	public final String toString() {
		return stackFrame.toString();
	}
}
