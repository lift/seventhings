#!/usr/bin/env python3
import re
import sys
from pathlib import Path

def fix_self_closing_tags(content):
    """Fix self-closing tags to be XML-compliant"""
    # List of void elements that should be self-closing
    void_elements = ['br', 'hr', 'input', 'img', 'link', 'meta']

    for element in void_elements:
        # Match opening tag that doesn't already end with />
        # Pattern: <element [attributes]> but not <element [attributes] />
        pattern = f'<{element}([^>]*?)(?<!/)>'
        replacement = f'<{element}\\1 />'
        content = re.sub(pattern, replacement, content)

    # Fix double spaces before />
    content = re.sub(r'  />', ' />', content)
    # Fix cases where we might have added / before existing />
    content = re.sub(r' / />', ' />', content)

    return content

def main():
    webapp_dir = Path('src/main/webapp')

    for html_file in webapp_dir.rglob('*.html'):
        print(f'Fixing {html_file}')
        content = html_file.read_text()
        fixed_content = fix_self_closing_tags(content)
        html_file.write_text(fixed_content)

    print('Done!')

if __name__ == '__main__':
    main()
